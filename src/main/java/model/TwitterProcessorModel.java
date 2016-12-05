package model;

import java.io.IOException;
import java.util.*;
//import java.lang.Object;

import org.bson.Document;
import org.bson.BasicBSONObject;

import org.neo4j.driver.v1.*;

import com.mongodb.client.MongoCollection;
import com.mongodb.BasicDBObject;

public class TwitterProcessorModel{
	

	public void process(MongoCollection<Document> collection, Session session) throws IOException {

		session.run( String.format("MATCH (n) DETACH DELETE n") );

		for (Document doc : collection.find() ) {
			
			Document user = (Document)doc.get("user");
			if (doc.get("in_reply_to_screen_name") != null ) {						
			
				String replyToUserId = "u_"+doc.getString("in_reply_to_screen_name").toLowerCase();
				String userId = "u_"+user.getString("screen_name").toLowerCase();
				//System.out.println("reply");
				String exists = "0";
				StatementResult result = session.run( String.format("MATCH ( (%s:User{screen_name:\"%s\"})-[rep:REPLY{type:\"reply\"}]->(%s:User{screen_name:\"%s\"}) ) RETURN 1 AS exists", userId, userId, replyToUserId, replyToUserId) );
				if (result.hasNext()) {
					Record record =result.next();
					exists = record.get("exists").toString();
					//System.out.println(exists);
				}								

				//System.out.println(exists);

				if  (!exists.equals("1")) {//si es la primera vez que se relacionan
					if (userId.equals(replyToUserId)) { //si el usuario se respondio a si mismo
						//session.run( String.format("MERGE (%s:User {screen_name:\"%s\"})-[rep:REPLY {type:\"reply\", count:1} ]->(%s:User {screen_name:\"%s\"})", userId, userId, userId, userId) );
					}

					else{
						session.run( String.format("MERGE (%s:User {screen_name:\"%s\"}) MERGE (%s:User {screen_name:\"%s\"}) MERGE (%s) - [rep:REPLY {type:\"reply\", count:1} ] -> (%s)", userId, userId, replyToUserId, replyToUserId, userId, replyToUserId) );
					}
				}
				else{
					if (userId.equals(replyToUserId)) { //si el usuario se respondio a si mismo
						//session.run( String.format("MERGE (%s:User {screen_name:\"%s\"})-[rep:REPLY {type:\"reply\", count:1} ]->(%s:User {screen_name:\"%s\"})", userId, userId, userId, userId) );
					}

					else{
						session.run( String.format("MATCH (%s:User {screen_name:\"%s\"})-[rep:REPLY{type:\"reply\"}]->(%s:User {screen_name:\"%s\"}) SET rep.count = rep.count + 1", userId, userId, replyToUserId, replyToUserId) );
					}
				}

				

							
				//System.out.println(userId + "  REPLY  " +replyToUserId);	
				//session.run( String.format("CREATE (%s:User {screen_name:\"%s\"})", userId, userId) );
				//session.run( String.format("CREATE (%s:User {screen_name:\"%s\"})", replyToUserId, replyToUserId) );
				//System.out.println(String.format("CREATE (%s)-[:REPLY]->(%s)", userId, replyToUserId));
				//session.run( String.format("MERGE (%s)-[:REPLY]->(%s)", userId, replyToUserId) );
			}
			//ver si en este tuit retuiteo a alguien
			Document retuit = (Document)doc.get("retweeted_status");
			//System.out.println(retuit.toString());
			if (retuit!=null) {
				//System.out.println("*****************entre*********************");
				String userId = "u_"+user.getString("screen_name").toLowerCase();
				//este usuarioRetuiteado es un documento dentro del documento retuit
				Document userRetuiteado = (Document)retuit.get("user");
				String userRetuiteadoId = "u_"+userRetuiteado.getString("screen_name").toLowerCase();
				//System.out.println("retweet");
				String exists = "0";
				StatementResult result = session.run( String.format("MATCH ( (%s:User{screen_name:\"%s\"})-[retw:RETWEET{type:\"retweet\"}]->(%s:User{screen_name:\"%s\"}) ) RETURN 1 AS exists", userId, userId, userRetuiteadoId, userRetuiteadoId) );
				if (result.hasNext()) {
					Record record =result.next();
					exists = record.get("exists").toString();
					//System.out.println(exists);
				}
				
				//System.out.println(exists);
				if (!exists.equals("1")) { //si la relacion entre los dos usuarios no existe
					if (userId.equals(userRetuiteadoId)) { //si el usuario se respondio a si mismo
						//session.run( String.format("MERGE (%s:User {screen_name:\"%s\"})-[retw:RETWEET {type:\"retweet\", count:1} ]->(%s:User {screen_name:\"%s\"})", userId, userId, userId, userId) );
					}
					else{
						session.run( String.format("MERGE (%s:User {screen_name:\"%s\"}) MERGE (%s:User {screen_name:\"%s\"}) MERGE (%s) - [retw:RETWEET{type:\"retweet\", count:1} ] -> (%s)", userId, userId, userRetuiteadoId, userRetuiteadoId, userId, userRetuiteadoId) );
					}
				}
				else{
					if (userId.equals(userRetuiteadoId)) { //si el usuario se respondio a si mismo
						//session.run( String.format("MERGE (%s:User {screen_name:\"%s\"})-[retw:RETWEET {type:\"retweet\", count:1} ]->(%s:User {screen_name:\"%s\"})", userId, userId, userId, userId) );
					}
					else{
						session.run( String.format("MATCH (%s:User {screen_name:\"%s\"})-[retw:RETWEET{type:\"retweet\"}]->(%s:User {screen_name:\"%s\"}) SET retw.count = retw.count + 1", userId, userId, userRetuiteadoId, userRetuiteadoId) );
					}
				}

				/*
				original
				if (userId.equals(userRetuiteadoId)) { //si el usuario se reuiteo a si mismo
					session.run( String.format("MERGE (%s {screen_name:\"%s\"})-[retw:RETWEET]->(%s {screen_name:\"%s\"})", userId, userId, userRetuiteadoId, userRetuiteadoId) );
				}
				else{
					session.run( String.format("MERGE (%s:User {screen_name:\"%s\"}) MERGE (%s:User {screen_name:\"%s\"}) CREATE UNIQUE (%s) - [retw:RETWEET] -> (%s)", userId, userId, userRetuiteadoId, userRetuiteadoId, userId, userRetuiteadoId) );
				}*/

				//System.out.println(userId + "  RETWEET  " +userRetuiteadoId);
				//session.run( String.format("CREATE (%s:User {screen_name:\"%s\"})", userId, userId) );
				//session.run( String.format("CREATE (%s:User {screen_name:\"%s\"})", userRetuiteadoId, userRetuiteadoId) );
				//System.out.println(String.format("CREATE (%s)-[:RETWEET]->(%s)", userId, userRetuiteadoId) );
				//session.run( String.format("MERGE (%s)-[:RETWEET]->(%s)", userId, userRetuiteadoId) );

			}

			//guardar menciones
			Document entities = (Document)doc.get("entities");

			if (!entities.get("user_mentions").toString().equals("[]")) {
				//System.out.println("mention");
				String userId = "u_"+user.getString("screen_name").toLowerCase();
				session.run( String.format("MERGE (%s:User {screen_name:\"%s\"})", userId, userId) );
				//System.out.println("creando: "+userId);
				String token, userMentionedId;
				ArrayList<String> screenNames = new ArrayList<String>();

				StringTokenizer st = new StringTokenizer(entities.get("user_mentions").toString()," ");
			    while (st.hasMoreTokens()) {
			    	token=st.nextToken();
			    	//System.out.print(token+" ");
			    	if (token.startsWith("screen_name") ) {
			    		screenNames.add(token);
			    	}			    	
			    }
			    //System.out.println();
			    for (int i=0;i<screenNames.size();i++ ) {
			    	String[] parts = screenNames.get(i).split("=");
			    	userMentionedId="u_"+parts[1].replace(",", "");	
			    	//System.out.println("Haciendo match");
			    	String exists = "0";
			    	//System.out.println("***sacando statement");
					StatementResult result = session.run( String.format("MATCH ( (%s:User{screen_name:\"%s\"})-[ment:MENTION{type:\"mention\"}]->(%s:User{screen_name:\"%s\"}) ) RETURN 1 AS exists", userId, userId, userMentionedId, userMentionedId) );
					//System.out.println("***fin statement");
					//System.out.println("uniendo a: "+userMentionedId);
					if (result.hasNext()) {
						Record record =result.next();
						exists = record.get("exists").toString();
						//System.out.println(exists);
					}			    	
			    	//System.out.println(exists);
					if (!exists.equals("1")) { //si la relacion entre los dos usuarios NO existe
						if (userId.equals(userMentionedId)) { //si el usuario se respondio a si mismo
							//session.run( String.format("MERGE (%s:User {screen_name:\"%s\"})-[ment:MENTION {type:\"mention\", count:1} ]->(%s:User {screen_name:\"%s\"})", userId, userId, userId, userId) );
						}
						else{
							session.run( String.format("MATCH (%s:User {screen_name:\"%s\"}) MERGE (%s:User {screen_name:\"%s\"}) MERGE (%s) - [ment:MENTION{type:\"mention\", count:1} ] -> (%s)", userId, userId, userMentionedId, userMentionedId, userId, userMentionedId) );
						}
					}
					else{//si la relacion entre los dos usuarios SI existe
						if (userId.equals(userMentionedId)) { //si el usuario se respondio a si mismo
							//session.run( String.format("MERGE (%s:User {screen_name:\"%s\"})-[ment:MENTION {type:\"mention\", count:1} ]->(%s:User {screen_name:\"%s\"})", userId, userId, userId, userId) );
						}
						else{
							session.run( String.format("MATCH (%s:User {screen_name:\"%s\"})-[ment:MENTION{type:\"mention\"}]->(%s:User {screen_name:\"%s\"}) SET ment.count = ment.count + 1", userId, userId, userMentionedId, userMentionedId) );
						}
					}

			    	/* ORIGINAL
			    	if (userId.equals(userMentionedId)) {//si el usuario se menciona a si mismo
			    		session.run( String.format("MERGE (%s)-[ment:MENTION]->(%s)", userId, userMentionedId) );			    		
			    	}		    	
			    	else{
				    	//session.run( String.format("CREATE (%s:User {screen_name:\"%s\"})", userMentionedId, userMentionedId) );
				    	//System.out.println(String.format("MERGE (%s)-[:MENTION]->(%s)", userId, userMentionedId));
				    	
				    	session.run( String.format("MATCH (%s:User {screen_name:\"%s\"}) MERGE (%s:User {screen_name:\"%s\"}) CREATE UNIQUE (%s) - [ment:MENTION] -> (%s)", userId, userId, userMentionedId, userMentionedId, userId, userMentionedId) );

				    	//session.run( String.format("MERGE (%s)-[:MENTION]->(%s)", userId, userMentionedId) );
			    	}*/
			    }
			    


			}
		}
	}

	public ArrayList<String> getLeaders (Session session) {
		String user;
		ArrayList<String> leaders = new ArrayList<String>();
		StatementResult result = session.run( String.format("MATCH (n)<-[*1..2]-(p) RETURN n as user, count(p) as alcance order by alcance desc LIMIT 10") );
		if (result.hasNext()) {
			Record record =result.next();
			user = record.get("user").toString();
			leaders.add(user);
		}
		return(leaders);
	}

	public ArrayList<String> getSpreaders (Session session)  {
		String user;
		ArrayList<String> spreaders = new ArrayList<String>();
		StatementResult result = session.run( String.format("MATCH (n)-[*1..2]->(p) RETURN n as user, count(p) as alcance order by alcance desc LIMIT 10") );
		if (result.hasNext()) {
			Record record =result.next();
			user = record.get("user").toString();
			spreaders.add(user);
		}
		return(spreaders);
	}
}


