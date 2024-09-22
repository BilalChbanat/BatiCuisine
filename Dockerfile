FROM postgres:latest

# Set environment variables for the default PostgreSQL user and database
ENV POSTGRES_USER=admin
ENV POSTGRES_PASSWORD=admin
ENV POSTGRES_DB=baticuisine

# Expose PostgreSQL port
EXPOSE 5432
