import pandas as pd
import json
import ast 

#Load CSV file
df = pd.read_csv('dataset/restaurants.csv', sep=";", skiprows=1)

#Rename columns
df.columns = ["ID", "RestaurantName", "AverageCostForTwo", "AggregateRating", "RatingText", "Votes", "Date", "Coordinates", "City/County/Continent"]

#Cast data types
df["Votes"] = df["Votes"].astype(int)
df["AverageCostForTwo"] = df["AverageCostForTwo"].astype(int)
df["AggregateRating"] = df["AggregateRating"].astype(float)

#Add columnn City, County, State for query #3
split_location = df['City/County/Continent'].str.split('/', expand=True)

#Ensure it has exactly 3 columns, filling missing values if needed
while split_location.shape[1] < 3:
    split_location[split_location.shape[1]] = None

df[['City', 'County', 'Continent']] = split_location.iloc[:, :3]

#Convert stringified coordinate list to {"lat": ..., "lon": ...}
def convert_coordinates(coord_str):
    try:
        coords = ast.literal_eval(coord_str)  
        return {"lat": float(coords[0]), "lon": float(coords[1])}
    except Exception as e:
        print(f"Errore parsing '{coord_str}': {e}")
        return None

df["Coordinates"] = df["Coordinates"].apply(convert_coordinates)
df = df[df["Coordinates"].notnull()]

#Prepare NDJSON file for Elasticsearch bulk
with open('dataset/restaurants_elastic_bulk.json', 'w') as outfile:
    for _, row in df.iterrows():
        index_line = json.dumps({"index": {}})
        data_line = json.dumps(row.to_dict(), default=str)
        outfile.write(f"{index_line}\n{data_line}\n")

print("File NDJSON generato correttamente.")
