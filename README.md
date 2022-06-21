# Devops-Journey
In this branch, we have a simple Springboot CRUD WebApp using Mysql and a Rest API.

This project contains :
- The crudusers.sql file to import the database 
- The Jenkins file
- The rest folder with the code of our API
- The webapp folder with the code of our webapp
- The expoted Jenkins Jobs folder

We can clone this repo and run the project with Intellij or simply pull the docker images from Docker Hub and run the containers. 

To pull the project from Docker Hub : 

          ~ docker pull aboutaro/springboot-rest-crudapp:("mysql_data", "api_container", "webapp_container")

Docker commands to build images and run the containers : 

- First of all, create a private network : 

          ~ docker network create external-api 

- For Mysql container : 

          ~ docker run -d -p 6033:3306 --network=external-api --name=mysql-docker --env="MYSQL_ROOT_PASSWORD=root" --env="MYSQL_PASSWORD=root" --env="MYSQL_DATABASE=crudusers" mysql (Créer/Lancer le container "mysql-docker" dans notre réseau privé)
          ~ docker exec -i mysql-docker mysql -u root -proot crudusers < crudusers.sql (Importer la base de donnée)

- For Api container :   

          ~ docker build -f DockerFile -t rest-docker . (Créer l'image de notre api à partir du .jar)
          ~ docker run -t --network "external-api" --name "api" --link mysql-docker:mysql -p 9090:9090 rest-docker (Créer/Lancer le container "api" dans notre réseau privé)

- For App container :   

          ~ docker build -f DockerFile -t webapp-docker . (Créer l'image de notre api à partir du .jar)
          ~ docker run -t --network "external-api" --name "app" -p 9091:9091 webapp-docker (Créer/Lancer le container "app" dans notre réseau privé)
