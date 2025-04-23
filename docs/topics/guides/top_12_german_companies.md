# Financial Analysis of Top German Companies

In this notebook, we load and analyze key financial metrics for several major German companies.
We perform data transformations, compute statistical measures, group by business sector, and create various plots to visualize trends in
revenue, net income, return on assets (ROA), and return on equity (ROE)

<web-summary>
TODO: write summary
</web-summary>

<card-summary>
TODO: write summary
</card-summary>

<link-summary>
TODO: write summary
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.TopGermanCompanies-->

<!---FUN notebook_test_top_12_german_companies_1-->

<!---END-->

<!---FUN notebook_test_top_12_german_companies_2-->

<!---END-->

<!---FUN notebook_test_top_12_german_companies_3-->

<!---END-->

<inline-frame src="./resources/top_12_german_companies/notebook_test_top_12_german_companies_3.html" width="100%" height="500px"></inline-frame>

## Data Preparation: Formatting and Categorization

In this step, we prepare and clean the data for analysis.

- Custom Date Format: We define a custom date format (MM/DD/YYYY) to parse the "period" column into `LocalDate` without zero-padding for months.
- Business Sectors: We create an `enum` to classify companies into sectors such as Automotive, Banking, IT, and others.
- Data Transformation:
    - Convert the "period" column to `LocalDate` using the custom format.
    - Parse the "percentageDebtToEquity" column by removing the percentage sign and converting it to a `Double`.
    - Sort the data by "company" and "period".
    - Add a new column, "sector," which assigns companies to specific business sectors based on their names.


This step ensures the dataset is well-structured and categorized for further analysis.

<!---FUN notebook_test_top_12_german_companies_4-->

<!---END-->

### Aggregating Financial Data

These steps group data by company and calculate key metrics (mean, median, std, min, max) for financial columns like revenue, net income, and ratios.

<!---FUN notebook_test_top_12_german_companies_5-->

<!---END-->

<inline-frame src="./resources/top_12_german_companies/notebook_test_top_12_german_companies_5.html" width="100%" height="500px"></inline-frame>

<!---FUN notebook_test_top_12_german_companies_6-->

<!---END-->

<inline-frame src="./resources/top_12_german_companies/notebook_test_top_12_german_companies_6.html" width="100%" height="500px"></inline-frame>

<!---FUN notebook_test_top_12_german_companies_7-->

<!---END-->

<inline-frame src="./resources/top_12_german_companies/notebook_test_top_12_german_companies_7.html" width="100%" height="500px"></inline-frame>

<!---FUN notebook_test_top_12_german_companies_8-->

<!---END-->

## Visualizing Revenue and Net Income by Sector

1. Revenue by Sector:
    - A line chart shows total revenue over time, grouped by business sector.
    - Points highlight specific values, and each sector is color-coded using a predefined palette.
    - The chart includes a legend for sector identification.
2. Net Income by Sector:
    - A similar line chart displays total net income over time for each sector.
    - Points and color-coding are used to enhance clarity, with a legend indicating the sectors.

These visualizations help analyze trends and compare financial performance across sectors over time.

<!---FUN notebook_test_top_12_german_companies_9-->

<!---END-->

![notebook_test_top_12_german_companies_9](notebook_test_top_12_german_companies_9.svg)

<!---FUN notebook_test_top_12_german_companies_10-->

<!---END-->

![notebook_test_top_12_german_companies_10](notebook_test_top_12_german_companies_10.svg)

## ROA and ROE Analysis by Sector

1. Computing Averages and Standard Deviations:
    - Group the data by sector and calculate the mean and standard deviation for Return on Assets (ROA) and Return on Equity (ROE).
    - This creates a summarized dataset for sector-level performance comparison.
2. Visualizing ROA by Sector:
    - A bar chart displays the average ROA for each sector.
    - Error bars represent one standard deviation, showing the variability within each sector.
3. Visualizing ROE by Sector:
    - A similar bar chart illustrates the average ROE across sectors.
    - Error bars provide insight into the standard deviation of ROE within each sector.

These charts help compare sector-level profitability metrics and assess consistency within sectors.

<!---FUN notebook_test_top_12_german_companies_11-->

<!---END-->

<inline-frame src="./resources/top_12_german_companies/notebook_test_top_12_german_companies_11.html" width="100%" height="500px"></inline-frame>

<!---FUN notebook_test_top_12_german_companies_12-->

<!---END-->

![notebook_test_top_12_german_companies_12](notebook_test_top_12_german_companies_12.svg)

<!---FUN notebook_test_top_12_german_companies_13-->

<!---END-->

![notebook_test_top_12_german_companies_13](notebook_test_top_12_german_companies_13.svg)

