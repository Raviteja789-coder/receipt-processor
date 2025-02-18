# Assignment of Receipt Processor Project (Fetch Company)

## Prerequisites
- Git 
- Docker

## Run the following steps to generate docker image in the local machine:
1. Clone the repo into the local machine using this command - `git clone https://github.com/Raviteja789-coder/receipt-processor.git`.
2. Change directory into the cloned folder of repository.
3. Run the command `docker build -t assigment/receipt-processor .` to build the docker images in the machine.
4. Run the command `docker run -d --name receipt-processor -p 8888:8888 assigment/receipt-processor`  to run the docker container of an assignment
5. Use the following curl commands to test it:
    ```
   curl --header "Content-Type: application/json" --request POST --data '{"retailer": "Target", "purchaseDate": "2022-01-01", "purchaseTime": "13:01", "items": [ {"shortDescription": "Mountain Dew 12PK", "price": "6.49"}, { "shortDescription": "Emils Cheese Pizza", "price": "12.25"}, {"shortDescription": "Knorr Creamy Chicken", "price": "1.26"}, {"shortDescription": "Doritos Nacho Cheese", "price": "3.35"}, {"shortDescription": "   Klarbrunn 12-PK 12 FL OZ  ", "price": "12.00"}], "total": "35.35"}' http://localhost:8888/receipts/process
   ```
   ```
   curl -XGET http://localhost:8888/receipts/1/points
   ```
   ```
   curl --header "Content-Type: application/json" --request POST --data '{"retailer": "M&M Corner Market", "purchaseDate": "2022-03-20", "purchaseTime": "14:33", "items": [ {"shortDescription": "Gatorade", "price": "2.25"}, {"shortDescription": "Gatorade", "price": "2.25"}, {"shortDescription": "Gatorade", "price": "2.25"}, {"shortDescription": "Gatorade", "price": "2.25"}], "total": "9.00"}' http://localhost:8888/receipts/process
   ```
   ```
   curl -XGET http://localhost:8888/receipts/2/points
   ```