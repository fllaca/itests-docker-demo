docker build -t swapi_double $(pwd)/src/test/resources/stubby/
docker run --name swapi_double -p 8882:8882  -it --rm swapi_double