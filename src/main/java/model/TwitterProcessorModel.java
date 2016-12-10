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
				String id2 = "u_"+doc.getString("in_reply_to_user_id_str").toLowerCase();
				String userId = "u_"+user.getString("screen_name").toLowerCase();
				String id = "u_"+user.getString("id_str").toLowerCase();
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
						session.run( String.format("MERGE (%s:User {screen_name:\"%s\", id:\"%s\"}) MERGE (%s:User {screen_name:\"%s\", id:\"%s\"}) MERGE (%s) - [rep:REPLY {type:\"reply\", count:1} ] -> (%s)", userId, userId, id, replyToUserId, replyToUserId, id2, userId, replyToUserId) );
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
				String id = "u_"+user.getString("id_str").toLowerCase();
				//este usuarioRetuiteado es un documento dentro del documento retuit
				Document userRetuiteado = (Document)retuit.get("user");
				String userRetuiteadoId = "u_"+userRetuiteado.getString("screen_name").toLowerCase();
				String id2 = "u_"+userRetuiteado.getString("id_str").toLowerCase();
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
						session.run( String.format("MERGE (%s:User {screen_name:\"%s\", id:\"%s\"}) MERGE (%s:User {screen_name:\"%s\", id:\"%s\"}) MERGE (%s) - [retw:RETWEET{type:\"retweet\", count:1} ] -> (%s)", userId, userId, id, userRetuiteadoId, userRetuiteadoId, id2, userId, userRetuiteadoId) );
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

			}

			//guardar menciones
			Document entities = (Document)doc.get("entities");

			if (!entities.get("user_mentions").toString().equals("[]")) {
				//System.out.println("mention");
				String userId = "u_"+user.getString("screen_name").toLowerCase();
				String id = "u_"+user.getString("id_str").toLowerCase();
				session.run( String.format("MERGE (%s:User {screen_name:\"%s\", id:\"%s\"})", userId, userId, id) );
				//System.out.println("creando: "+userId);
				String token, userMentionedId, id2;
				ArrayList<String> screenNames = new ArrayList<String>();

				StringTokenizer st = new StringTokenizer(entities.get("user_mentions").toString()," ");
			    while (st.hasMoreTokens()) {
			    	token=st.nextToken();
			    	//System.out.print(token+" ");
			    	if (token.startsWith("screen_name") ) {
			    		screenNames.add(token);
			    	}	
			    	if (token.startsWith("id_str") ) {
			    		screenNames.add(token);
			    	}		    	
			    }
			    //System.out.println();
			    for (int i=0;i<screenNames.size();i+=2 ) {
			    	String[] parts = screenNames.get(i).split("=");
			    	userMentionedId="u_"+parts[1].replace(",", "");	
			    	String[] parts2 = screenNames.get(i+1).split("=");
			    	id2="u_"+parts2[1].replace(",", "");
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
							session.run( String.format("MATCH (%s:User {screen_name:\"%s\"}) MERGE (%s:User {screen_name:\"%s\", id:\"%s\"}) MERGE (%s) - [ment:MENTION{type:\"mention\", count:1} ] -> (%s)", userId, userId, userMentionedId, userMentionedId, id2, userId, userMentionedId) );
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

			    }			  
			}
		}
	}

	public List<Tuitero> getLeaders (Session session) {
		String user, userId;
		String total_score;
		int score;
		List<Tuitero> leaders = new ArrayList<>();
		//limpieza
		session.run("MATCH (n) SET n.reply_score=0, n.retweet_score=0, n.mention_score=0");
		//creacion de las ponderaciones de los 3 tipos de relacion
		session.run("MATCH (n)-[r:REPLY]->(p) WITH p as nodo, r.count as count, r as reply SET reply.score=10*count");
		session.run("MATCH (n)-[r:RETWEET]->(p) WITH p as nodo, r.count as count, r as ret SET ret.score=3*count");
		session.run("MATCH (n)-[r:MENTION]->(p) WITH p as nodo, r.count as count, r as men SET men.score=1*count");
		//limpieza
		session.run("MATCH (n) SET n.leader=0");
		//guardar el score obtenido de todas sus relaciones ponderadas
		session.run("MATCH (n)<-[r]-(p) WITH n as node, sum(r.score) as score SET node.leader=score");
		//sumar el puntaje del nodo mas los puntajes de los nodos que se conectan a el
		StatementResult result = session.run("MATCH (n)<-[r]-(p) WITH n.screen_name as name, n.id as id, sum(r.score) as alcance, sum(p.leader) as leader, sum(r.count) as cantidad RETURN name as user, id as userId, (alcance+leader/cantidad) as total_score order by total_score desc LIMIT 10");

		while (result.hasNext()) {
			Record record =result.next();
			user = record.get("user").toString();
			userId = record.get("userId").toString();
			total_score = record.get("total_score").toString();
			score = Integer.parseInt(total_score);
			user=user.substring(3, (user.length()-1));
			userId=userId.substring(3, (userId.length()-1));
			Tuitero tuitero = new Tuitero();
			tuitero.setName(user);
			tuitero.setScore(score);
			tuitero.setId(userId);
			leaders.add(tuitero);
		}

		return leaders;
	}

	public List<Tuitero> getSpreaders (Session session)  {
		String user, userId;
		String total_score;
		int score;
		List<Tuitero> spreaders = new ArrayList<>();

		session.run("MATCH (n) SET n.reply_score=0, n.retweet_score=0, n.mention_score=0");
		//creacion de las ponderaciones de los 3 tipos de relacion
		session.run("MATCH (n)-[r:REPLY]->(p) WITH p as nodo, r.count as count, r as reply SET reply.score=10*count");
		session.run("MATCH (n)-[r:RETWEET]->(p) WITH p as nodo, r.count as count, r as ret SET ret.score=3*count");
		session.run("MATCH (n)-[r:MENTION]->(p) WITH p as nodo, r.count as count, r as men SET men.score=1*count");
		//limpieza
		session.run("MATCH (n) SET n.spreader=0");
		//guardar el score obtenido de todas sus relaciones ponderadas
		session.run("MATCH (n)-[r]->(p) WITH n as node, sum(r.score) as score SET node.spreader=score");
		//sumar el puntaje del nodo mas los puntajes de los nodos a los que se conecta
		StatementResult result = session.run("MATCH (n)-[r]->(p) WITH n.screen_name as name, n.id as id, sum(r.score) as alcance, sum(p.spreader) as spreader, n.leader as importancia, p.leader as import, sum(r.count) as cantidad RETURN name as user, id as userId, ((import*alcance/cantidad+spreader*importancia/cantidad)) as total_score order by total_score desc LIMIT 10");

		while (result.hasNext()) {
			Record record =result.next();
			user = record.get("user").toString();
			userId = record.get("userId").toString();
			total_score = record.get("total_score").toString();
			score = Integer.parseInt(total_score);
			user=user.substring(3, (user.length()-1));
			userId=userId.substring(3, (userId.length()-1));
			Tuitero tuitero = new Tuitero();
			tuitero.setName(user);
			tuitero.setId(userId);
			tuitero.setScore(score);
			spreaders.add(tuitero);
		}
		return spreaders;
	}
}


