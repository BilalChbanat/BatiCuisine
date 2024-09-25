# Bati-Cuisine: Construction Project Management System

## Overview

Bati-Cuisine is a Java-based application designed for professionals in the construction and renovation industry. The application helps manage project details, track client information, estimate costs for materials and labor, and generate quotes. It provides a clear overview of project costs, including taxes, and allows users to adjust estimates based on material quality and labor productivity.

## Features

- **Project Management**: Create and manage renovation or construction projects, track details, and monitor associated costs.
- **Client Management**: Associate clients (individuals or professionals) with projects for easier tracking and invoicing.
- **Material Management**: Add materials with unit cost, quantity, and transport fees for precise cost estimation.
- **Labor Management**: Record worker hours, hourly rates, and productivity to calculate total labor costs.
- **Quote Generation**: Generate quotes based on the total costs of materials, labor, and other expenses.
- **Tax Management**: Apply taxes (e.g., VAT) to project components for accurate cost estimations.
- **Cost Calculation**: Adjust estimates based on material quality and worker productivity.
- **Quote Validity**: Set an issue date and a validity date for quotes to ensure clients know the timeframe of the offer.
- **Client Differentiation**: Apply specific conditions or discounts based on whether the client is an individual or a professional.
- **Acceptance or Rejection of Quotes**: Allow clients to accept or reject quotes, enabling progress on the project.

## Technology Stack

- **Java**
- **PostgreSQL**
- **Docker**
- **Java Collections, Hashmaps, Streams**
- **Singleton and Repository Design Patterns**
- **Java Time API**
- **Layered Architecture (Services, Repositories, Views)**
- **JUnit for Testing**

## Design Patterns

- **Singleton**: Used for managing database connections.
- **Repository**: Separation of business logic and database operations for better code maintainability.

## Installation

### Prerequisites

- [Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (Version 11 or higher)
- [PostgreSQL](https://www.postgresql.org/download/)
- [Docker](https://www.docker.com/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) (Optional for development)

### Steps

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/bati-cuisine.git
   cd bati-cuisine
