mvn clean && mvn install && docker build -t bundlenews/saleshippingservice . && docker run -p 8080:8080 bundlenews/saleshippingservice