package com.zaultavangar.trends;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class BigQueryExample implements CommandLineRunner {

  @Override
  public void run(String... args) {
    try {
      // Initialize BigQuery service
      BigQuery bigQuery = BigQueryOptions.newBuilder()
          .setProjectId("gdelt-analysis-429805")
          .build()
          .getService();

      // Define the SQL query
      String query = "SELECT term, score, refresh_date FROM `bigquery-public-data.google_trends.top_terms` LIMIT 10";

      // Create a query job configuration
      QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query).build();

      // Run the query
      TableResult result = bigQuery.query(queryConfig);

      // Process the results
      result.iterateAll().forEach(row -> {
        String term = row.get("term").getStringValue();
        long score = row.get("score").getLongValue();
        String refreshDate = row.get("refresh_date").getStringValue();

        System.out.println("Term: " + term + ", Score: " + score + ", Refresh Date: " + refreshDate);
      });

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
