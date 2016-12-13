package model;

import java.util.ArrayList;
import java.util.Arrays;

public class SSSModel {
	
	private ArrayList<String> objetos;
	
	/*
	public SSSModel(){
		this.objetos = new ArrayList<String>(Arrays.asList(
				"Lollapalooza", "Creamfields", "Fauna Primavera", 
				"La cumbre del rock chileno", "Frontera", 
				"Fiis 2016", "DEFQON.1",
				"Metallica","The Strokes","The Weeknd",
				"The XX","The Chainsmokers","Flume",
				"Duran Duran","Two Door Cinema Club","Rancid",
				"The 1975","G-Eazy","Melanie Martinez",
				"Cage the Elephant","MØ","Oliver Heldens",
				"Nervo","Catfish and the Bottlemen","Glass Animals",
				"Griz","Vance Joy","Tegan and Sara",
				"Don Diablo","Álex Anwandter","Bomba Estéreo",
				"Lucybell","Tchami","Gondwana",
				"Weichafe","Silversun Pickups","Borgore",
				"La Pozze Latina","Alok","DJ Who",
				"We are the Grand","Villa Cariño","Zaturno Feat. MC Piri",
				"Mad Professor","(Me Llamo) Sebastian","López",
				"Liricistas","Newen Afrobeat","Rootz Hifi & Macky Banton",
				"Prehistoricos","Crizálida","Román & Castro",
				"Mariel Mariel","DR Vena","Chicago Toys",
				"Paz Court","Boraj","8 Monkys",
				"Rey Puesto","Enrique Icka","Tus Amigos Nuevos",
				"Vives&Forero","Rod Valdés","Amahiro",
				"Tiësto","Dubfire: Live Hybrid","Apollonia",
				"BlasterJaxx","Borgeous","Felix Jaehn",
				"KSHMR","Lost Frequences","Marshmello",
				"Mike Cervello","Moksi","Sam Feldt",
				"Sven Väth","The Martinez Brothers","Yellow Claw",
				"Youngr","Amalia Baltboltin","Inguerzon",
				"Gisela Lindhorst","Cris Celiz","Tomás Villarroel",
				"Frans Van Der Hoek","Hedo","Hard Condr",
				"Tomas G","Air","Primal Scream","Courtney Barnett",
				"Larry Gus","Ellen Allien","Tiga",
				"Luisa Puterman","Andrea Paz","Eggglub",
				"Los Barbara Blade","Mitú","Roisin Murphy",
				"Matanza","IIOII","Fantasna","Guerra",
				"La Femme","Lia Nadja","Com Truise",
				"Mas569 & Aurelius98","Underground Resistance","Aye Aye",
				"Camila Moreno","Edward Sharpe & The Magnetic Zeros","The Brian Jonestown Massacre",
				"Kurt Vile ans The Violators","Trax Records Showcase","Los Tres",
				"Chancho en Piedra","Joe Vasconcellos","Los Tetas",
				"Nicole","Shaggy","Emir Kusturica",
				"Mon Laferte","Perotá Chingó","Sum 41",
				"Ataque 77","Javiera Mena","Los Cafres",
				"Los Amigos Invisibles","Chistina Rosenvinge","Nach",
				"La Vela Puerca","Morodo","Os Paralamas Do Sucesso",
				"Caligaris","Tronic","Vectores",
				"Dënver","Nonpalidece","Tote King",
				"Ile","Como Asesinar a Felipes","Churupaca",
				"Planeta No","Habitación del Pánico","Playing for Change",
				"Carla Morrison","Travis","Adrenalize & Demi Kanon",
				"Atmozfears","Audiotricz","Bass Modulators",
				"Da Tweekaz","Frontliner","Psyko Punkz",
				"Sickddellz","TNT","Zany",
				"B-Front","D-Stroyer","Danidemente",
				"Deetox","DJ K-Oss","Donkey Rollers",
				"Frequencerz","Technoboy","Tuneboy",
				"DJ Jubert","Hans Noise","Kallki",
				"Korsakoff","Mad Dog","Miss Offender",
				"Under-X","9Milliz","B-Freqz",
				"Hans Reverze Noise","Happy Tweekay","Rick Mitchells",
				"Stormerz","Tricz","Yurner"));
	}*/
	
	//función que determina la distancia de levenshtein (distancia entre 2 palabras)
	//obtenido de https://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Levenshtein_distance#Java
	public int levenshteinDistance (String lhs, String rhs) {                          
	    int len0 = lhs.length() + 1;                                                     
	    int len1 = rhs.length() + 1;                                                     
	                                                                                    
	    // the array of distances                                                       
	    int[] cost = new int[len0];                                                     
	    int[] newcost = new int[len0];                                                  
	                                                                                    
	    // initial cost of skipping prefix in String s0                                 
	    for (int i = 0; i < len0; i++) cost[i] = i;                                     
	                                                                                    
	    // dynamically computing the array of distances                                  
	                                                                                    
	    // transformation cost for each letter in s1                                    
	    for (int j = 1; j < len1; j++) {                                                
	        // initial cost of skipping prefix in String s1                             
	        newcost[0] = j;                                                             
	                                                                                    
	        // transformation cost for each letter in s0                                
	        for(int i = 1; i < len0; i++) {                                             
	            // matching current letters in both strings                             
	            int match = (lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1;             
	                                                                                    
	            // computing cost for each transformation                               
	            int cost_replace = cost[i - 1] + match;                                 
	            int cost_insert  = cost[i] + 1;                                         
	            int cost_delete  = newcost[i - 1] + 1;                                  
	                                                                                    
	            // keep minimum cost                                                    
	            newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
	        }                                                                           
	                                                                                    
	        // swap cost/newcost arrays                                                 
	        int[] swap = cost; cost = newcost; newcost = swap;                          
	    }                                                                               
	                                                                                    
	    // the distance is the cost for transforming all letters in both strings        
	    return cost[len0 - 1];                                                          
	}

	//función que retorna la máxima distancia entre dos elementos
	public int maximaDistancia(ArrayList<String> objetos){
		int max=0;
		ArrayList<String> mostrar = new ArrayList();
		String temp;
		int distTemp;
		for(int j=0;j<objetos.size();j++){
			for(int i=0;i<objetos.size();i++){
				if(i>j){
					distTemp = levenshteinDistance(objetos.get(i),objetos.get(j));
					/*temp = objetos.get(i)+" "+objetos.get(j);
					System.out.println(temp+" "+distTemp);*/
					if(distTemp>max){
						max=distTemp;
					}
				}
			}
		}
		return max;
	}
	
	//función que retorna una lista de pivotes
	//necesita la lista de los elementos (objetos), distancia máxima (M)
	//y un alpha "a" experimental (a entre 0 y 1)
	public ArrayList<String> seleccionPivotes(ArrayList<String> objetos, int M, double a){
		ArrayList<String> pivotes= new ArrayList<String>();
		ArrayList<String> tempPivotes= new ArrayList<String>();
		int distP;
		double ma = M*a;
		pivotes.add(objetos.get(0));
		tempPivotes.add(objetos.get(0));
		for(String x: objetos){
			for(int i=0;i<pivotes.size();i++){
				String p = pivotes.get(i);
				distP=levenshteinDistance(x,p);
				if(distP>ma){
					pivotes.add(x);
					//System.out.println(x);
				}
			}
		}
		return pivotes;
	}
	
	//función que crea el índice a partir de los pivotes
	//se le da a la función como argumentos:
	//la lista de pivotes (esto queda como "estático" si la base de datos no cambia)
	//la lista de objetos(palabras) existentes (esto queda como "estático" si la base de datos no cambia)
	//palabra a consultar (q) con su radio (r)
	public ArrayList<String> buildSSS(ArrayList<String> pivotes, ArrayList<String> objetos, String q, int r){
		ArrayList<Integer> dq = new ArrayList<Integer>();
		int distQP;
		for (String p: pivotes){
			distQP=levenshteinDistance(q,p);
			dq.add(distQP);
		}
		
		ArrayList<String> L = new ArrayList<String>();
		int distOP;
		int n;
		for(String o: objetos){
			n=0;
			for(int i=0;i<pivotes.size();i++){
				String p = pivotes.get(i);
				distOP=levenshteinDistance(o,p);
				if((distOP>(dq.get(i)-r))&&(distOP<(dq.get(i)+r))){
					n=n+1;
				}
			}
			if(n==pivotes.size()){
				L.add(o);			
			}
		}
		int distOQ;
		ArrayList<String> objetoFinal = new ArrayList<String>();
		for(String o: L){
			distOQ=levenshteinDistance(o,q);
			if(distOQ<=r){
				objetoFinal.add(o);
			}
		}
		return objetoFinal;
	}
	
	/* En el main las funciones se llamaban así:
	 * int maxima = maximaDistancia(objetos);
		ArrayList<String> pivotes = seleccionPivotes(objetos,maxima,0.8);
		
		ArrayList<String> resultados = buildSSS(pivotes,festivales,"Lolapalooza",3);
		después de esta función -------^
		me devolvía un arraylist con la palabra "Lollapalooza"
		for(String r: resultados){
			System.out.println(r);
		}
	 * 
	 */
	
}
