package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class  Neo4jModel{
	
		public Neo4jModel() {

		}

		public void crearGrafos() throws IOException {
			@SuppressWarnings("resource")
			MongoModel mongo = new MongoModel();
			
			Driver driver = GraphDatabase.driver( "bolt://localhost", AuthTokens.basic( "neo4j", "matias" ) );
			Session session = driver.session();

			TwitterProcessorModel twitterProcessorModel = new TwitterProcessorModel();

			twitterProcessorModel.process(mongo.getMongoCollection(), session);

			session.close();
			driver.close();

		}


		public ArrayList<String> getLeaders()  {
			@SuppressWarnings("resource")			
			
			Driver driver = GraphDatabase.driver( "bolt://localhost", AuthTokens.basic( "neo4j", "matias" ) );
			Session session = driver.session();

			TwitterProcessorModel twitterProcessorModel = new TwitterProcessorModel();
			ArrayList<String> usersLeaders = new ArrayList();
			usersLeaders = twitterProcessorModel.getLeaders(session);

			return(usersLeaders);
		}

		public ArrayList<String> getSpreaders() {
			@SuppressWarnings("resource")			
			
			Driver driver = GraphDatabase.driver( "bolt://localhost", AuthTokens.basic( "neo4j", "matias" ) );
			Session session = driver.session();

			TwitterProcessorModel twitterProcessorModel = new TwitterProcessorModel();
			ArrayList<String> usersSpreaders = new ArrayList();
			usersSpreaders = twitterProcessorModel.getSpreaders(session);

			return(usersSpreaders);
		}
	

}
