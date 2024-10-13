
# Coverage United




## Overview

This Java project provides a comprehensive car insurance solution, allowing users to:
- Check eligibility for insurance based on age.
- Determine the appropriate insurance type (Full, Partial, or Basic).
- Calculate annual premiums.
- Manage customer information and insurance records.

## Features

- User-friendly menu-driven interface.
- CSV file storage for efficient data management.
- Detailed insurance information retrieval for existing customers.
- Flexible options for updating and deleting records.

## Class Structure

- Person: Base class for storing customer details (name, age, driving experience).
- Customer: Extends Person to include car-related information.
- InsuranceChecker: Abstract class for determining insurance eligibility.
- FullCoverage, PartialCoverage, BasicCoverage: Concrete implementations of InsuranceChecker.
- PremiumCalculator: Calculates annual premiums.
- Questionnaire: Collects customer details.
- printables: Handles user interaction and output.
- csvRelated: Manages CSV file operations.






## Run Locally

Clone the project

```bash
  git clone https://github.com/jarede-dev/coverage-united
```

Go to the project directory

```bash
  cd my-project
```

Run CarInsuranceChecker.java
```bash
  java CarInsuranceChecker.java
```



## License

[GPLv3](https://choosealicense.com/licenses/gpl-3.0/)

