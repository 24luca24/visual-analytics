# visual-analytics
As part of my Master's in Software Development and Engineering (MSDE), where the "D" stands for Data, this project focuses on learning how to ingest, clean, and visualize data using Python libraries like Pandas and Matplotlib

# 📊 Data Exploration and Visualization Assignment

> As part of my Master's in Software Development and Engineering (MSDE), this assignment focuses on exploring, cleaning, and visualizing data using Python tools introduced in class.

![Python](https://img.shields.io/badge/Python-3.8+-blue?logo=python)
![Jupyter](https://img.shields.io/badge/Jupyter-Notebook-orange?logo=jupyter)
![Pandas](https://img.shields.io/badge/Pandas-Data%20Analysis-yellow?logo=pandas)
![Matplotlib](https://img.shields.io/badge/Matplotlib-Visualization-blueviolet?logo=matplotlib)
![Seaborn](https://img.shields.io/badge/Seaborn-Statistical%20Plots-lightblue)
![GeoPandas](https://img.shields.io/badge/GeoPandas-Geospatial%20Analysis-2c5d63)
![Bokeh](https://img.shields.io/badge/Bokeh-Interactive%20Visualization-f2a93b)

---
# Assignment 1

### 🎯 Goal

The goal of this assignment is to use **Python and Jupyter Notebook** to explore, analyze, and visualize the provided datasets.
You can find the dataset following this link:
...to update...

### ▶️ How to Run the Notebook

1. Clone the repository:
   ```sh
   git clone https://github.com/your-username/your-repo-name.git
   cd your-repo-name

# 📈 Assignment 2: Visual Analytics with Elasticsearch & Kibana

> This project combines **data querying**, **visualization**, and the **extension of Elasticsearch capabilities** through a custom ingestion plugin.

![Elasticsearch](https://img.shields.io/badge/Elasticsearch-Search%20Engine-orange?logo=elasticsearch)
![Kibana](https://img.shields.io/badge/Kibana-Dashboard%20&%20Canvas-blueviolet?logo=kibana)
![Java](https://img.shields.io/badge/Java-Plugin%20Development-red?logo=openjdk)
![Gradle](https://img.shields.io/badge/Gradle-Build%20Tool-green?logo=gradle)
![CSV](https://img.shields.io/badge/Data-CSV-yellow?logo=filezilla)

---

## 🎯 Goal

The aim of this assignment is to:

1. Explore and analyze a dataset of restaurants using **Elasticsearch queries and aggregations**.
2. Visualize insights through **Kibana dashboards** and **canvas presentations**.
3. Develop a custom **Elasticsearch ingest plugin** that performs lookup-based text substitutions during document ingestion.

---

## 📁 Dataset

The dataset used in this assignment is provided as a CSV file at this link:  
📄 to update soon...

Please ensure your Elasticsearch index is named: `restaurants`

---

## 🛠️ Features Implemented

### 🔎 Section 1: Indexing, Queries & Aggregations
- Indexed the `restaurants.csv` into JSON documents
- Crafted advanced search queries and filters (e.g., geolocation, string patterns, numeric ranges)
- Built aggregations for:
  - Weighted averages
  - Top-N groupings
  - Bucket-based analysis

### 📊 Section 2: Kibana Visualization
- Created interactive **dashboards** with:
  - Review sentiment trends
  - Cost distribution across continents
  - Map with vote-based markers
  - Heatmap for ratings vs price
- Built a **Canvas** with:
  - Custom filters
  - Categorized cost metrics
  - Visual summaries of review quality

### 🔌 Section 3: Elasticsearch Ingest Plugin
- Implemented a **lookup ingest processor**
- Enabled dynamic replacement of coded fields with human-readable values during indexing
- Configured pipeline setup and document transformation via custom plugin

---

## 🔄 Example of Plugin in Action

**Input document:**
```json
{ "field1": "Need to optimize the C001 temperature. C010 needs to be changed." }
```

**Lookup Map**
{ "C001": "tyre", "C010": "front wing" }

**Output**
{ "field1": "Need to optimize the tyre temperature. front wing needs to be changed." }

# Assignment 3



# Group Project
