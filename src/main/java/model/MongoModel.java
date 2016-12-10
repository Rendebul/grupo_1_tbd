package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static java.util.Arrays.asList;
import java.util.*;

import org.bson.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoException;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.ThreadLocalRandom;

public class MongoModel    {
    private Mongo mongo;
    private DB db;
    private DBObject textSearchCommand;
    private DBCollection collection;

    private static MongoModel INSTANCE;

    public MongoModel() {
        this.mongo = new Mongo("localhost", 27017);
        this.textSearchCommand = new BasicDBObject();
        this.db = mongo.getDB("tbd");
        this.collection = db.getCollection("tweets");
        //agregarEmoteScore();
        //añadir coordenadas
        //agregarCoordenadas();
        agregarComunas();
    }
    
    public void convertirFechas()
    {
        DBCursor cursor = this.collection.find();
        while(cursor.hasNext())
        {
            DBObject document = cursor.next();
            DBObject updated = new BasicDBObject();
            updated.put("$set", new BasicDBObject("created_at", new Date((String)document.get("created_at"))));
            this.collection.update(document, updated);
        }
    }

    /*public void agregarCoordenadas()
    {
        CoordenadasModel coor[] = new CoordenadasModel[34];
        for(int i = 0; i < 34; i++)
            coor[i] = new CoordenadasModel();

        //coordenadas de 34 comunas de la RM
        coor[0].setX(-33.542156);
        coor[0].setY(-70.647184);
        coor[1].setX(-33.403697);
        coor[1].setY(-70.713522);
        coor[2].setX(-33.488703);
        coor[2].setY(-70.670953);
        coor[3].setX(-33.538966);
        coor[3].setY(-70.619992);
        coor[4].setX(-33.553833);
        coor[4].setY(-70.673269);
        coor[5].setX(-33.49217);
        coor[5].setY(-70.70842);
        coor[6].setX(-33.462054);
        coor[6].setY(-70.701824);
        coor[7].setX(-33.420431);
        coor[7].setY(-70.66166);
        coor[8].setX(-33.362547);
        coor[8].setY(-70.501523);
        coor[9].setX(-33.591989);
        coor[9].setY(-70.705338);
        coor[10].setX(-33.492765);
        coor[10].setY(-70.62899);
        coor[11].setX(-33.40678);
        coor[11].setY(-70.640804);
        coor[12].setX(-33.368822);
        coor[12].setY(-70.731123);
        coor[13].setX(-33.49);
        coor[13].setY(-70.6);
        coor[14].setX(-33.48598);
        coor[14].setY(-70.64976);
        coor[15].setX(-33.533446);
        coor[15].setY(-70.582735);
        coor[16].setX(-33.440885);
        coor[16].setY(-70.557476);
        coor[17].setX(-33.518828);
        coor[17].setY(-70.694159);
        coor[18].setX(-33.586674);
        coor[18].setY(-70.634802);
        coor[19].setX(-33.429087);
        coor[19].setY(-70.730442);
        coor[20].setX(-33.45099);
        coor[20].setY(-70.59298);
        coor[21].setX(-33.37472);
        coor[21].setY(-70.63464);
        coor[22].setX(-33.509344);
        coor[22].setY(-70.755464);
        coor[23].setX(-33.440915);
        coor[23].setY(-70.755812);
        coor[24].setX(-33.39629);
        coor[24].setY(-70.67024);
        coor[25].setX(-33.41895);
        coor[25].setY(-70.702854);
        coor[26].setX(-33.43198);
        coor[26].setY(-70.60951);
        coor[27].setX(-33.44206);
        coor[27].setY(-70.71962);
        coor[28].setX(-33.534594);
        coor[28].setY(-70.665977);
        coor[29].setX(-33.473558);
        coor[29].setY(-70.55387);
        coor[30].setX(-33.40866);
        coor[30].setY(-70.568613);
        coor[31].setX(-33.40866);
        coor[31].setY(-70.568613);
        coor[32].setX(-33.38957);
        coor[32].setY(-70.571421);
        coor[33].setX(-33.437107);
        coor[33].setY(-70.650253);

        DBCursor cursor = this.collection.find();
        while(cursor.hasNext())
        {
            DBObject document = cursor.next();

            //elegir comuna random
            int random = ThreadLocalRandom.current().nextInt(0, 34);
            List<Double> listDob = new ArrayList<>();
            listDob.add(coor[random].getX());
            listDob.add(coor[random].getY());

            //añadir coordenadas en "geo"
            DBObject updated = new BasicDBObject();
            updated.put("$set", new BasicDBObject("geo", listDob));
            this.collection.update(document, updated);
        }
    }*/

    public void agregarComunas()
    {
        String comunas[] = {"Cerrillos","Cerro Navia","Conchalí","El Bosque","Estación Central","Huechuraba", "Independencia","La Cisterna","La Florida","La Granja","La Pintana","La Reina","Las Condes","Lo Barnechea","Lo Espejo","Lo Prado","Macul", "Maipú","Ñuñoa","Pedro Aguirre Cerda","Peñalolén","Providencia","Pudahuel","Quilicura","Quinta Normal","Recoleta","Renca","San Joaquín","San Miguel","San Ramón","Santiago","Vitacura"};


        DBCursor cursor = this.collection.find();
        while(cursor.hasNext())
        {
            DBObject document = cursor.next();

            //elegir comuna random
            int random = ThreadLocalRandom.current().nextInt(0, comunas.length);

            //añadir coordenadas en "geo"
            DBObject updated = new BasicDBObject();
            updated.put("$set", new BasicDBObject("geo", comunas[random]));
            this.collection.update(document, updated);
        }
    }

    public List<ComunaModel> tweetsPorComuna()
    {
        String comunas[] = {"Cerrillos","Cerro Navia","Conchalí","El Bosque","Estación Central","Huechuraba", "Independencia","La Cisterna","La Florida","La Granja","La Pintana","La Reina","Las Condes","Lo Barnechea","Lo Espejo","Lo Prado","Macul", "Maipú","Ñuñoa","Pedro Aguirre Cerda","Peñalolén","Providencia","Pudahuel","Quilicura","Quinta Normal","Recoleta","Renca","San Joaquín","San Miguel","San Ramón","Santiago","Vitacura"};   
        List<ComunaModel> list = new ArrayList<>();

        for(int i = 0; i < comunas.length; i++)
        {
            ComunaModel comuna = new ComunaModel();

            int cantTweets = this.collection.find(new BasicDBObject("geo", comunas[i])).count();
            DBCursor cursor = this.collection.find(new BasicDBObject("geo", comunas[i]));

            double emoteScoreProm = 0.0;    
                while(cursor.hasNext())
                {
                    DBObject document = cursor.next();
                    emoteScoreProm += (Double)document.get("emote_score");
                }

            emoteScoreProm /= cantTweets;

            comuna.setNombre(comunas[i]);
            comuna.setTweets(cantTweets);
            comuna.setEmoteScoreAvg(emoteScoreProm);
            list.add(comuna);
        }

        return list;
    }

    public List<FestivalComunaModel> tweetsFestivalComuna(List<Festival> festivales)
    {
        List<FestivalComunaModel> listFC = new ArrayList<>();
        String comunas[] = {"Cerrillos","Cerro Navia","Conchalí","El Bosque","Estación Central","Huechuraba", "Independencia","La Cisterna","La Florida","La Granja","La Pintana","La Reina","Las Condes","Lo Barnechea","Lo Espejo","Lo Prado","Macul", "Maipú","Ñuñoa","Pedro Aguirre Cerda","Peñalolén","Providencia","Pudahuel","Quilicura","Quinta Normal","Recoleta","Renca","San Joaquín","San Miguel","San Ramón","Santiago","Vitacura"};   
        for(Festival festival : festivales)
        {
            FestivalComunaModel festivalComuna = new FestivalComunaModel();
            festivalComuna.setFestival(festival.getFestivalName());
            String searchString = festival.getFilters();
            List<ComunaModel> listC = new ArrayList<>();

            for(int i = 0; i < comunas.length; i++)
            {
                ComunaModel comuna = new ComunaModel();

                int cantTweets = this.collection.find(new BasicDBObject("$text", new BasicDBObject("$search", searchString))
                    .append("geo", comunas[i])).count();
                DBCursor cursor = this.collection.find(new BasicDBObject("$text", new BasicDBObject("$search", searchString))
                    .append("geo", comunas[i]));
            
                //agregando promedio emote_score
                double emoteScoreProm = 0.0;
                while(cursor.hasNext())
                {
                    DBObject document = cursor.next();
                    emoteScoreProm += (Double)document.get("emote_score");
                }

                emoteScoreProm /= cantTweets;

                comuna.setNombre(comunas[i]);
                comuna.setTweets(cantTweets);
                comuna.setEmoteScoreAvg(emoteScoreProm);
                listC.add(comuna);
            }
            festivalComuna.setComunas(listC);
            listFC.add(festivalComuna);
        }

        return listFC;
    }
    
    public void agregarEmoteScore()
    {
        String positivas = "ABIERTAMENTE ABOGADO ABOGÓ ABRAZO ABSOLUTA ABSOLUTAMENTE ABSORBE ABSORBENTE ABSORTO ABUNDA ABUNDAN ABUNDANCIA ABUNDANDO ABUNDANTE ACARICIADO ACCESIBLE ACCIÓN ACELERADO ACEPTABLE ACEPTACIÓN ACEPTADA ACEPTAR ACERO ACLAMACIÓN ACLAMADA ACLARA ACOGEDOR ACOMODACIÓN ACOMODADO ACOMODAR ACOMODATICIA ACTITUD ACTITUD POSITIVA ACTIVAR ACTIVO ACTUALIDAD ACTUALIZABLE ACTUALIZACIÓN ACTUALIZADO ACUERDAN ADAPTABILIDAD ADAPTACIÓN ADECUADA ADECUADAMENTE ADECUADO ADELANTA ADELANTAMIENTO ADELANTAR ADELANTE ADEMÁS ADJUDICADO ADMIRABLEMENTE ADMIRACIÓN ADMIRADOR ADMIRATIVAMENTE ADORABLE ADORACIÓN ADORADO ADORADOR ADORAR ADULACIÓN ADULADOR ADULAR AFABLE AFABLEMENTE AFAN AFECTACIÓN AFECTO AFECTUOSA AFECTUOSAMENTE AFILADAS AFINIDAD AFIRMACIÓN AFIRMAR AFIRMATIVA AFLUENCIA AFLUENTE AFORTUNADAMENTE AFORTUNADO AGILIDAD ÁGILMENTE AGRACIADA AGRACIADO AGRADA AGRADABILIDAD AGRADABLE AGRADABLEMENTE AGRADECIDO AGRADECIMIENTO AGRADECIMIENTOS AGRADO AGUA AHORRO AHORROS AIRE AJUSTABLE AL INSTANTE ALABANDO ALCANCE ALCANZABLE ALCANZAR ALEGRE ALEGREMENTE ALEGRE-VOLUNTAD ALEGRÍA ALEGRÍAS ALENTADO ALENTADOR ALENTAR ALERTA ALIMENTACIÓN ALIMENTADOS ALIMENTOS ALINEADOS ALISA ALIVIADO ALIVIAR ALIVIO ALIVIÓ ALLÁ-FABULOSO ALMA ALMA DE LA FIESTA ALOJAMIENTO ALTA CALIDAD ALTA CIVISMO ALTA ENERGÍA ALTRUISMO ALTRUISTA AMABILIDAD AMABLE AMABLEMENTE AMADO AMANECER AMANTE AMAR AMAR-ACEPTACIÓN AMARILLO AMAR-SENTIMIENTOS AMBICIÓN AMBICIOSAMENTE AMBICIOSO AMENIDAD AMENIZADA AMIGABLE PARA LOS NIÑOS AMIGABLEMENTE AMIGO AMISTAD AMISTOSA AMISTOSO AMOR AMOR PROPIO AMORES AMPLIAMENTE AMPLIO AÑADIR ÁNGEL ANGÉLICA ANHELAMOS ANIMACIÓN ANIMADA ANIMADO ANIMANDO ANIMAR ÁNIMO ÁNIMO PARA LA VIDA ANTICIPACIÓN APACIBLE APALANCAMIENTO APASIONADA APASIONADO APELACIÓN APENAS APERTURA APLAUDEN APOTEOSIS APOYO APRECIA APRECIABLE APRECIADA APRECIANDO APRECIAR APRECIO APRENDER APRENDIZAJE APROBACIÓN APROBAR APROPIADO APROVECHAMIENTO APTA ARDIENTE ARDIENTEMENTE ARMONÍA ARMONIOSA ARMONIOSAMENTE ARROLLADOR ARTICULO ASEGURADOS ASEGURAMIENTO ASEGURAR ASEQUIBLE ASEQUIBLES A-SER-CONOCIDO ASERTIVIDAD ASERTIVO ASOMBRA ASOMBRADO ASOMBRAR ASOMBRO ASOMBROSAMENTE ASOMBROSO ASOMBROSOS ASPIRACIÓN ASPIRACIONES ASTRONÓMICO ASTUTAMENTE ASUNCIÓN DE RIESGOS ATEMORIZADO ATENCIÓN ATENTO ATESTIGUAR ATORMENTAR ATRACCIÓN ATRACCIÓN SEXUAL ATRACTIVA ATRACTIVO ATRAÍDOS ATREVIDAMENTE ATURDIDO AUDACIA AUDAZ AUGE AUSPICIOSO AUTENTICIDAD AUTÉNTICO AUTO CUIDADO AUTO-AMABILIDAD AUTO-COMPASIÓN AUTODETERMINACIÓN AUTO-ENHACEMENT AUTOESTIMA AUTOEXPRESIÓN AUTÓNOMA AUTONOMÍA AUTO-PERDÓN AUTORITARIO AUTORIZADO AUTOSUFICIENCIA AUTOSUFICIENTES AVANCE AVANCES AVANZADA AVENTAJADO AVENTURAS AVENTURERO ÁVIDAMENTE AVIVAR AYUDA AYUDADO AYUDANDO BAJO COSTO BAJO PRECIO BARATO BARRIDO BASTA BASTADO BASTANTE BEATIFICAR BEATITUD BELLA BELLAMENTE BELLEZA BENDICIÓN BENDITA BENEFICIARIO BENEFICIO BENEFICIOS BENEFICIOSAMENTE BENEFICIOSO BENÉFICO BENEVOLENCIA BENÉVOLO BESO BIEN BIEN CONECTADO BIEN CONOCIDO BIEN CONSIDERADO BIEN EDUCADO BIEN EQUILIBRADA BIEN ESTABLECIDO- BIEN GESTIONADA BIEN HECHO BIEN INFORMADO BIEN INTENCIONADO BIEN PARECIDO BIEN RECIBIDO BIEN REDONDEADO BIEN RETROILUMINADO BIENESTAR BIENQUERIENTES BIENVENIDOS BIOFILIA BONDAD BONDAD AMOROSA BONITAMENTE BONOS BOTÍN BOYANTE BRILLA BRILLANTE BRILLO BRILLOS BROMA BUEN ASPECTO BUENA SALUD BUENA VOLUNTAD BUENO BURLAR CABALLERÍA CABALLEROSIDAD CABLES CACHORROS CALENTADOR CALIDAD CALIENTE CALIFICADO CALIFICAR CALMA CALOR CAMBIO CAPACIDAD CAPAZ CARIDAD CARIÑO CARIÑOSO CARISMA CARISMÁTICO CAUTIVADOS CAUTIVAR CAUTIVÓ CEDER CEGAMIENTO CELANTE CELEBRACIÓN CELEBRADA CELEBRADORA CELEBRANDO CELEBRAR CELESTIAL CELO CENTRADO CERCANÍA CERTEZA CHAMP ‘ CHISPA CHISPAS CHISTOSA CHISTOSO CIELO CIERTAMENTE CIERTO CITAS CIVILIZAR CIVISMO CLARA CLARAMENTE CLARIDAD CLARO CLASE CLÁSICO COHERENCIA COHERENTE COHESIVA COLABORACIÓN COLORIDO COMFORTABLEMENTE COMO CÓMODAMENTE CÓMODO COMPACTA COMPAÑERISMO COMPARTIR COMPASIÓN COMPASIVO COMPETENCIA COMPETENTE COMPETITIVA COMPLACIENTE COMPLEMENTADO COMPLEMENTARIA COMPLEMENTO COMPLEMENTOS COMPRENSIBLE COMPRENSIVO COMPROBADO COMPROMETIDO COMPROMISO COMUNICACIÓN COMUNIDAD COMUNIÓN CON CONFIANZA CON DULZURA CON ENTUSIASMO CON ÉXITO CON GUSTO CON JUSTICIA CON MEJORES RESULTADOS CONCEDER CONCENTRACIÓN CONCIENCIA CONCILIADOR CONCILIAR CONCISO CONDUCIR CONECTIVIDAD CONEXIÓN CONFIABILIDAD CONFIABLE CONFIABLEMENTE CONFIADO CONFIANZA CONFIAR CONFIRMÓ CONFORT CONGRATULATORIA CONGRUENCIA CONMOVEDORAMENTE CONOCIMIENTO CONQUISTAR CONSCIENTE CONSIDERACIÓN CONSIDERADO CONSISTENCIA CONSISTENTE CONSISTENTEMENTE CONSOLIDACIÓN CONSTANCIA CONSTRUCTIVO CONSUELO CONSUMADO CONTENIDO CONTENTAMIENTO CONTENTO CONTINUA CONTINUIDAD CONTRAATAQUES CONTRIBUCIÓN CONVENIENCIA CONVENIENTE CONVENIENTEMENTE CONVICCIÓN CONVINCENTE CONVINCENTEMENTE COOPERACIÓN COOPERATIVA COOPERATIVAMENTE CORAJE CORAZÓN CORAZÓN AMABLE CORAZÓN DE LEÓN CORDIALIDAD CORDIALMENTE CORRECTAMENTE CORRECTO CORTEJE CORTÉS CORTESÍA COSQUILLAS COSTO DE AHORRO CREAR CREATIVIDAD CREATIVO CRECER CRECIMIENTO CRECIMIENTO PERSONAL CREÍBLE CUIDADO CUIDADOSAMENTE CUIDANDO CUMPLE CUMPLIDA CUMPLIDO CUMPLIMIENTO CUMPLIR CURIOSAMENTE CURIOSIDAD CURIOSO DEBER DECENCIA DECENTE DECISIVO DEDICADO DEFECTOS DEFENDER DEFENSOR DEFENSORES DEFERENCIA DELEITARSE DELICADEZA DELICADO DELICIAS DELICIOSA DELICIOSAMENTE DELICIOSAS DELICIOSO DEPORTIVO DERECHO DERRAME DERROTA DERROTADO DERROTANDO DERROTAS DESCANSAMOS DESCARADAMENTE DESCUBRIMIENTO DESEABLE DESEANDO DESEO DESINTERÉS DESLUMBRADO DESMONTABLE DESPEJADO DESPERTÓ DESPIERTE DESPLIEGUE DESPRENDIMIENTO DESPREOCUPACIÓN DESPREOCUPADA DESTACAR DESTINO DESTREZA DESVERGONZADO DETERMINACIÓN DEUDA DEVOCIÓN DEVOTO DEVUELTO DICHOSAMENTE DICHOSO DIESTRAMENTE DIESTRO DIGNA DIGNIDAD DIGNIFICAR DIGNO DILIGENCIA DILIGENTE DILIGENTEMENTE DILUYENTE DINÁMICA DIOS DIOS MANDA DIOS MIO DIOSA DIPLOMÁTICA DIRECCION DISCIPLINA DISCRECIÓN DISFRUTA DISFRUTA DE DISFRUTADO DISFRUTAR DISFRUTE DISPONIBLE DISPUESTOS DISTINCIÓN DISTINGUIDO DISTINTIVO DIVERSIDAD DIVERSIFICADA DIVERTIDA DIVERTIDO DIVINA DIVINAMENTE DIVINO DÓCIL DOMINA DOMINADO DOMINAR DORADO DOTADO DULCE DULZURA DURADERO ECLIPSAR ECLIPSÓ ECONÓMICA ECONÓMICO ECOSOFÍA ECUÁNIME ECUANIMIDAD EDIFICAR EDUCACION EDUCADA EDUCADO EDUCAR EFECTIVAMENTE EFICACIA EFICAZ EFICIENCIA EFICIENCIA ENERGÉTICA EFICIENTE EFUSIVAMENTE EFUSIVIDAD EFUSIVO EGOCÉNTRICO EJEMPLAR EL AHORRO DE ENERGÍA ELACIONADAS ELÁSTICO ELECCIÓN ELEGANCIA ELEGANTE ELEVACIÓN ELEVADA ELEVANDO ELOCUENTE ELOCUENTEMENTE ELOGIAR ELOGIO ELOGIOSAMENTE EMBARAZADA EMBELESADO EMBELLECER EMINENCIA EMINENTE EMOCIÓN EMOCIONADA EMOCIONADO EMOCIONADO-ANTICIPACIÓN EMOCIONANTE EMOCIONANTEMENTE EMOCIONES EMPATÍA EMPATIZAR EMPODERAMIENTO EMPRENDEDOR EN EN APOYO EN BUSCA EN UN SOLO PUNTO EN VIVO ENAMORADO ENCANTADO ENCANTADOR ENCANTADORAMENTE ENCANTO ENCARGADO ENCENDEDOR ENCOMIABLE ENDEREZAR ENDULZAR ENERGÉTICA ENERGÍA ENERGÍA POSITIVA ENFÁTICO ENGATUSAR ENHORABUENA ENNOBLECER ENORME ENRIQUECIMIENTO ENSALZAR ENSEÑAR ENTENDER ENTENDIDO ENTENDIMIENTO ENTRAÑABLE ENTRETENIMIENTO ENTRETIENE ENTUSIASMAR ENTUSIASMO ENTUSIASTA ENVALENTONAR ENVIDIA ENVIDIABLE ENVIDIABLEMENTE ENVIDIOSO EPIFANÍA EQUIDAD EQUILIBRADO EQUIPO EQUITATIVA EQUITATIVO ERGONÓMICO -ERR GRATIS ERUDITO ES ALENTADOR ESCÉNICO ESCUCHAR ESCULTURAL ESENCIA ESFUERZO ESMERADO ESPACIO ESPECIAL ESPECIALIZADO ESPECTACULAR ESPECTACULARMENTE ESPERANZA ESPÍRITU ESPIRITUAL ESPLÉNDIDAMENTE ESPLENDOR ESPONTÁNEA ESPONTANEIDAD ESTABILIDAD ESTABILIZAR ESTABLE ESTACIONARIO ESTADO MENTAL ESTELAR ESTILO ESTIMULA ESTIMULACIÓN ESTIMULADA ESTIMULANTE ESTIMULAR ESTRELLA DEL ROCK ESTRELLAZO ESTUDIO ESTUDIOSO ESTUPEFACTO ESTUPENDAMENTE ESTUPENDO ETÉREO ETERNA ETERNO ÉTICA EUDEMONISMO EUDEMONISTA EUFORIA EUFÓRICAMENTE EUFÓRICO EUTONÍA EVALUATIVO EVOCADOR EXACTAMENTE EXALTACIÓN EXALTADAMENTE EXALTADO EXALTANDO EXAMEN EXCEDER EXCELENCIA EXCELENTE EXCELENTEMENTE EXCEPCIONAL EXCESIVAMENTE EXCITA EXCITAR EXCLUSIVO EXISTENCIA EXITAZOS ÉXITO ÉXITOS EXITOSA EXONERAR EXPANSIVO EXPERIENCIA EXPERTAMENTE EXPLORACIÓN EXPLOSIVOS EXPRESANDO EXPRESIVIDAD EXPRESO EXQUISITAMENTE EXQUISITO EXTASIAR ÉXTASIS EXTÁTICO EXTRAORDINARIA EXTRAORDINARIAMENTE EXTROVERTIDA EXUBERANCIA EXUBERANTE EXUBERANTEMENTE EXULTACIÓN EXULTANTE FABULOSAMENTE FABULOSO FÁCIL FÁCIL DE ACERCARSE FÁCIL DE USAR FÁCIL HABLAR A FACILIDAD FACILIDAD DE LA MENTE FACILITA FACILITAR FÁCILMENTE FACTIBLE FACTIBLEMENTE FAMILIA FAMOSAMENTE FAMOSO FANTÁSTICO FASCINACIÓN FASCINADO FASCINANTE FASCINANTEMENTE FAVOR FAVORECIDA FAVORITO FE FELICIDAD FELICIDADES FELICITA FELICITAR FELIZ FELIZ SUERTE GO FELPA FENOMENAL FENOMENALMENTE FERIA FÉRTIL FERVIENTE FERVIENTEMENTE FERVOROSAMENTE FERVOROSO FESTIVIDAD FESTIVO FIDELIDAD FIEL FIELMENTE FINALMENTE FINO FIRME FIRMEMENTE FIRMEZA FLEXIBILIDAD FLOR FLORECIENTE FLOTABILIDAD FLUJO FLUYE FOMENTO FORMALMENTE FORTALEZA FORTUITAMENTE FORTUITO FRAGANTE FRATERNAL FRESCO FRESCURA FRIVOLIDAD FRUCTÍFERO FRUGALIDAD FUERTE FUERZA FUERZAS DE PAZ FUTURISTA FUTURO GALLARDAMENTE GANABLE GANADO GANADOR GANADORES GANANCIAS GANANDO GANAR GANGA GARANTÍA GARANTÍAS GARBO GATITOS GENERAR GENEROSAMENTE GENEROSIDAD GENEROSO GENIAL GENUINO GIGANTESCO GLAMOUR GLORIA GLORIFICAR GLORIOSA GLORIOSAMENTE GOMOSO GOOD-SENTIMIENTO GOZOSAMENTE GRACIA GRACIAS GRACIOSAMENTE GRAN GRAN CAPACIDAD GRANDE GRANDEZA GRANDIOSIDAD GRANIZO GRATAMENTE GRATIFICA GRATIFICACIÓN GRATIFICANTE GRATIFICANTEMENTE GRATIS GRATITUD GUAU GUÍA GUSTADO GUSTO GUSTOS HÁBIL HABILIDAD HABILITADO HABILITAR HÁBILMENTE HACE SEÑAS HACE SUYA HACER HACER SEÑAS HACERSE QUERER POR HACIENDO LA DIFERENCIA HALAGADORAMENTE HALAGAR HERMOSA HERMOSAMENTE HERMOSO HERMOSURA HÉROE HÉROES HEROICA HEROICIDAD HEROÍNA HEROÍSMO HIDALGO HILARANTE HIPNOTIZA HIPNOTIZADO HIPNOTIZANTE HIPNOTIZAR HIZO SEÑAS HOLA HOLÍSTICA HOMENAJE HONESTIDAD HONESTO HONRA HONRADO HOSPITALARIO HOSPITALIDAD HUMANO HUMILDAD HUMILDE HUMOR HUMORÍSTICAMENTE IDEALISMO IDEALIZAR IDÍLICA IDOLATRADO IDOLATRAR IGUALDAD ILIMITADO ILUMINACIÓN ILUMINADO ILUMINAR ILUSTRE IMAGINACIÓN IMAGINATIVA IMPACIENCIA IMPACIENTE IMPARABLE IMPARCIAL IMPARCIALIDAD IMPARCIALMENTE IMPÁVIDO IMPECABLE IMPECABLEMENTE IMPERTINENCIA IMPONENCIA IMPONENTE IMPORTANTE IMPORTAR IMPRESIONA IMPRESIONANTE IMPRESIONANTEMENTE INAFECTADO INATACABLE INCLUSIÓN INCOMPARABLE INCONDICIONAL INCONDICIONALMENTE INCONSÚTIL INCREÍBLE INCREÍBLEMENTE INCUESTIONABLE INDEMNE INDEPENDENCIA INDISCUTIBLE INDISCUTIBLEMENTE INDIVIDUALIZADO INDUDABLE INDULGENTE INEFABLE INEQUÍVOCAMENTE INEQUÍVOCO INESTIMABLE INESTIMABLEMENTE INFALIBILIDAD INFALIBLE INFALIBLEMENTE INFINITO INFLUENCIA INFLUYENTES INGENIO INGENIOSAMENTE INGENIOSO INGENUAMENTE INGENUO INICIO INIGUALABLE INMACULADA INMEJORABLE INMENSO INNOVACIÓN INNOVADOR INNOVAR INOCENTE INOCUO INOLVIDABLE INQUEBRANTABLE INQUISITIVO INSPIRACIÓN INSPIRADO INSTALACIONES INSTRUCTIVO INTEGRADO INTEGRAL INTEGRIDAD INTEGRIDAD PERSONAL INTELIGENCIA INTELIGENTE INTELIGIBLE INTENCIÓN INTENSIDAD INTERÉS INTERESADO INTERESANTE INTERESES INTIMIDAD ÍNTIMO INTRIGA INTRIGADO INTRIGANTE INTRINCADO INTUITIVIDAD INTUITIVO INVALORABLE INVENCIBLE INVENCIBLES INVENTIVA INVERTIR INVESTIGACIÓN INVIOLADO INVOLUCRAR IRIDISCENTE IR-LA-EXTRA-MILLA IRREAL IRREPRENSIBLE IRREPROCHABLE JAMMIN ‘ JENGIBRE JOVEN JÓVENES DE CORAZÓN JOVIALIDAD JUBILACIÓN JUBILOSA JUBILOSAMENTE JUBILOSO JUEGO JUGUETONA JUGUETÓNAMENTE JUICIOSO JUSTICIA JUSTO JUVENIL JUVENTUD  LA PIEDAD LAUDABLEMENTE LEAL LEALTAD LEGAL LEGENDARIO LEGIBLE LEGÍTIMAMENTE LEGÍTIMO LEVANTAMIENTO LEY LIBERACIÓN LIBERADO LIBERAR LIBERTAD LIBERTADES LÍDER LIDERAZGO LIMPIA LIMPIADOR LIMPIEZA LINDO LÍRICA LISA LISONJERAMENTE LISTO LLAMATIVA LLAMATIVO LO ÚLTIMO LOABLE LÓGICA LÓGICO LOGRAR LOGRO LOGROS LONGEVIDAD LOS CAJONES LUCHA CONTRA EL ATAQUE LÚCIDAMENTE LÚCIDO LUCRATIVAS LUCRATIVO LUJOSAMENTE LUJOSO LUJURIANTE LUMINOSO LUSTRE LUSTROSO LUTO LUZ MADURAMENTE MADUREZ MADURO MAESTRÍA MAESTRO MÁGICO MAGISTRAL MAGISTRALMENTE MAGNÁNIMAMENTE MAGNANIMIDAD MAGNÁNIMO MAGNIFICA MAGNÍFICA MAGNÍFICAMENTE MAGNIFICENCIA MAGNÍFICO MAJESTAD MANEJABLE MANIOBRABLE MANOS ABAJO MANTENGA MARAVILLARON MARAVILLAS MARAVILLOSA MARAVILLOSAMENTE MARAVILLOSO MÁS MÁS AFORTUNADO MÁS AGRADABLE MÁS AGUDA MÁS BARATO MÁS BONITO MÁS CALIENTE MÁS CONOCIDO MÁS DURO MÁS ESPACIOSO MÁS FÁCIL MÁS FELIZ MÁS FIRME MÁS FIRMES MÁS FRESCO MÁS FRESCOS MÁS GEEKS MÁS INTELIGENTE MÁS PRÁCTICO MÁS RÁPIDO MÁS RESISTENTE MÁS RESISTENTES MÁS SUAVE MÁS SUERTE MÁS VENDIDO MÁXIMA CALIDAD MEDITACIÓN MEJOR MEJOR CONOCIDO- MEJORA MEJORADA MEJORADO MEJORAR MEJORAR LAS CUALIFICACIONES MEJORAS MEJOR-LO ESPERADO- MELIFLUO MELIORISMO MELOCOTÓN MEMORABLE MENTALIDAD MENTE ABIERTA MENTE POSITIVA MENTE-SOPLADO MERECEDOR MERECIDAMENTE MÉRITO MERITORIOS METICULOSAMENTE METICULOSO MILAGRO MILAGROS MILAGROSA MILAGROSAMENTE MISERICORDIA MISERICORDIOSO MODA MODERNO MODESTIA MODESTO MONUMENTALMENTE MORALIDAD MOTIVACIÓN MOTIVADO MOTIVAR MOVIDO MOVIMIENTO MUCHOS MUERTO EN MULTIUSOS MUNDIALMENTE FAMOSO MUTUALIDAD MUY MUY BARATO MUY BIEN NACIMIENTO NATURALEZA-MADE NAVEGABLE NEOTENIA NIZA NO COMPROMETIDOS NO RESISTENCIA NO RESISTENTE NO VIOLENCIA NO VIOLENTA NOBLE NOTABLE NOTABLEMENTE NOVEDAD NUEVO NUTRITIVA OBEDIENTE OBRA MAESTRA OBRAS OBRAS MAESTRAS OBSCENO OBSESIÓN OBTENIBLE OJO-CATCH OM-MANI-PADME HUM- OMNIPOTENCIA OPORTUNIDAD OPORTUNO ÓPTIMA OPTIMISMO OPTIMISTA OPTIMIZADO OPTIMIZAR OPULENTO ORDEN ORDENADO ORGANIZACIÓN ORGÁSMICO ORGULLO ORGULLOSO ORIENTACIÓN ORIGINALIDAD ORO OSTENTOSO PACIENCIA PACIENTE PACIENTEMENTE PACÍFICA PACIFICO PACÍFICO PACTO PAGAR PALOMA PANACEA PANORÁMICA PANQUEQUES PARADISÍACO PARTICIPACIÓN PARTICIPE PARTIDARIO PASION PASIÓN PASMOSAMENTE PASMOSO PATRIOTA PATRIÓTICA PAZ PAZ INTERIOR PENDIENTES PENSAMIENTO POSITIVO PENSAMIENTOS POSITIVOS PENSATIVO PERDÓN PERDONAR PERFECCIÓN PERFECCIONAMIENTO PERFECTAMENTE PERFECTO PERMITIDA PERMITIENDO PERMITIR PERSEVERAD PERSEVERANCIA PERSISTENCIA PERSONAJES PERSONALIZADO PERSPICACIA PERSPICAZMENTE PERSUASIÓN PERTENECER PERTENENCIA PIADOSA PIADOSAMENTE PICANTE PIEDAD PIEDRA ANGULAR PINTORESCO PIONERO PLACER PLACERES PLENITUD PODER PODEROSAMENTE PODEROSO POÉTICA POETIZAR POR FAVOR PORTÁTIL POSITIVAMENTE POSITIVO POSITIVOS POSITIVOS PALABRAS POSITIVOS-CIRCUNSTANCIAS POSITIVOS-CREENCIAS POSITIVOS-EVENTOS POTENCIA POTENTE PRÁCTICO PRECIOSO PRECISA PRECISAMENTE PRECISIÓN PREEMINENTE PREFERENTEMENTE PRÉFÉRÉS PREFERIBLE PREFERIDO PREFIERE PREFIRIENDO PREMIO PREMIOS PREPARACIÓN PREPONDERANCIA PRESENCIA PRESERVACIÓN PRESTEZA PRESTIGIO PREVISIÓN PRIMERA CLASE PRIMERO EN SU CLASE PRINCIPIOS PRIVACIDAD PRIVILEGIADA PRIVILEGIO PROACTIVA PROACTIVIDAD PROBABILIDADES PROBAR PRÓDIGAMENTE PRODIGIOSA PRODIGIOSAMENTE PRODUCTIVA PRODUCTIVO PROFUNDA PROFUNDAMENTE PROFUSIÓN PROFUSO PROGRESIVO PROGRESO PROMESA PROMESAS PROMETEDORA PROMETIDA PROMINENTE PROMOTOR PRONTITUD PROPICIAMENTE PROPICIO PROPÓSITO PRÓSPERA PROSPERIDAD PRÓSPERO PROTAGONISMO PROTECCIÓN PROTEJA PRUDENCIA PRUDENTE PRUEBA PUJANTE PULGAR ARRIBA PULGARES ARRIBA PULIDO PUNTUAL PUNTUALIDAD PUNTUALMENTE QUERIDO QUIESCENTE RACIONAL RACIONALIDAD RACIONALIZADO RADIANTE RAÍCES RÁPIDAMENTE RAPIDEZ RÁPIDO RAPTO RAZÓN RAZONABLE RAZONABLEMENTE REACTIVAR REAFIRMACIÓN REAFIRMAMOS REALIDAD REALISTA REALIZACIÓN RECEPTIVO RECÓGEME RECOGIDOS RECOMENDABLE RECOMENDACIÓN RECOMENDACIONES RECOMENDADO RECOMENDAR RECOMPENSA RECOMPENSADORAMENTE RECONCILIACIÓN RECONFORTANTE RECONOCIDO RECONOCIMIENTO RECONOCIMIENTOS RÉCORD RECTAMENTE RECTIFICACION RECTIFICACIÓN RECTITUD RECTORES RECUPERACIÓN RECUPERAR REDENCIÓN REDENTOR REDIMIR REEMBOLSO REEMPLAZABLE REEMPLAZABLE POR EL USUARIO REESTRUCTURACION REESTRUCTURACIÓN REESTRUCTURADO REFINA REFINADO REFORMA REFORMADO REFORMAS REFUGIO REGALO REGIAMENTE REGOCIJAR REGOCIJO REJUVENECER REJUVENECIDO RELACIÓN RELACIONADOS RELACIONES RELAJADO RELAJANTE RELÁJESE RELIGIÓN RELUCIENTE RELUCIR REMEDIO REMISIÓN REMUNERADO REMUNERAR RENACIMIENTO RENDICIÓN DE CUENTAS RENOMBRE RENOVADA RENOVAR REPOSO REPUTACIÓN RESILIENCIA RESISTENCIA RESPALDAN RESPETABLE RESPETO RESPETUOSAMENTE RESPETUOSO RESPETUOSO DE LAS LEYES RESPIRO RESPLANDECIENTE RESPLANDOR RESPONDÓN RESPONSABILIDAD RESPONSABLEMENTE RESPUESTA RESTAURACIÓN RESTAURADO RESTO RESUCITADO RESULTADOS RETO RETRACTIL REVELACIÓN REVERENCIA REVERENTE REVERENTEMENTE REVIENTA REVITALIZANTES REVIVE REVOLUCIONA REVOLUCIONARÁ REVOLUCIONARIO REVOLUCIONÓ RICA EN CARACTERÍSTICAS RIESGO BAJO RIQUEZA RISA RISITAS ROBUSTEZ ROBUSTO ROMÁNTICAMENTE ROMÁNTICO ROMANTIZAR ROSA ROTUNDO SABER SABIAMENTE SABIDURÍA";
        String positivas2 = "SABOREAR SABROSO SAGACIDAD SAGRADO SALUD SALUDABLE SALUDO SALVACIÓN SALVADOR SANO SANTIDAD SANTO SATISFACCIÓN DE SÍ MISMO SATISFACE SATISFACER SATISFACTORIA SATISFACTORIAMENTE SATISFACTORIO SATISFECHO SAZONADA SEDUCTORAMENTE SEGURA SEGURIDAD SEGURO SELECTIVA SELLO SEÑAS SENCILLA SENCILLEZ SENCILLO SEÑORIAL SENSACIÓN SENSACIONAL SENSACIONALMENTE SENSACIONES SENSATAMENTE SENSIBLE SENTIRSE BIEN SER VISTO SERENA SERENIDAD SERIEDAD SERVICIO SERVIR SEXUAL EXPRESIÓN SÍ SIGNIFICADO SIGNIFICATIVA SIGNIFICATIVO SILENCIOSO SIMPLE SIMPLIFICA SIMPLIFICACIÓN SIMPLIFICADO SIMPLIFICAR SIN COMPLICACIONES SIN DOLOR SIN DUDA SIN ESFUERZO SIN INMUTARSE SIN LÍMITES SIN MIEDO SIN PEAJE SIN PROBLEMAS SIN RESTRICCIONES SIN RIESGO SIN RIVAL SIN TIEMPO SINCERAMENTE SINCERIDAD SINCERO SINTONIZARSE SISTEMATIZACIÓN SOBREESTIMACIÓN SOBRESALIDO SOBRESALIENTEMENTE SOBREVIVIENTE SOFISTICADO SOLÍCITAMENTE SOLÍCITO SÓLIDAMENTE SOLIDARIDAD SOLIDEZ SOLTURA SOLUCIONADOR DE PROBLEMAS SOÑADOR SONORA SONRIENDO SONRIENTE SONRISA SONRISAS SOPORTES SORPRENDENTE SORPRENDENTEMENTE SORPRENDIDO SOSTENIBILIDAD SOSTENIBLE SOSTENIDO SUAVE SUAVEMENTE SUBSIDIA SUBSIDIO SUBVENCIONADA SUBVENCIONAR SUCULENTA SUEÑA SUERTE SUFICIENTE SUFICIENTEMENTE SUMAMENTE SUNTUOSA SUNTUOSAMENTE SUNTUOSIDAD SUNTUOSO SUPERA SUPERADAS SUPERADO SUPERANDO SUPERAR SUPERAR A SUPERIOR AL MERCADO SUPERIORIDAD SUPERÓ SUPERPOTENCIA SUPERSÓNICO SUPERVIVENCIA SUPREMA SUPREMACÍA SUPREMAMENTE SURREALISTA SUSTANTIVA SER TALENTO TALENTOS TAPADO -TEMA LIBRE TENACIDAD TENTACIÓN TENTADOR TENTADORA TENTADORAMENTE TERAPIA TESORO TIEMPO TIENE ÉXITO TIERRA TIPO TOCADO TODO TODO AL REDEDOR TOLERANCIA TOQUE TRABAJABLE TRABAJADO TRABAJADOR TRABAJADOR DE LA LUZ TRABAJO TRABAJO DURO TRABAJO EN EQUIPO TRACCIÓN TRADICIÓN TRADICIONAL TRANCE TRANQUILA TRANQUILIDAD TRANQUILIZAR TRANSFORMACIÓN TRANSFORMADOR TRANSFORMAR TRANSPARENTE TRASCENDENTAL TREMENDAMENTE TRIUNFAL TRIUNFANTE TRIUNFO TRIVIALMENTE TROFEO TROMPETA UNIDAD UNIFICACIÓN UNIFICACIÓN DE LA MENTE UNIFORMEMENTE ÚTIL ÚTILES UTILIDAD VALE LA PENA VALENTÍA VALGA LA PENA VÁLIDO VALIENTE VALIENTEMENTE VALIOSO VALOR VALORES VARIEDAD VENERACION VENERAR VENTAJA VENTAJAS VENTAJOSAMENTE VENTAJOSO VERACIDAD VERAZ VERDAD VERIFICABLE VERIFICAR VERSÁTIL VERSATILIDAD VIBRANTE VICTORIA VICTORIOSO VIDA VIGILANCIA VIGILANTE VIGOR VIGORIZADO VIGORIZANTE VIGORIZAR VIRTUD VIRTUOSAMENTE VIRTUOSO VISIBLE VISION DE LA RADIOGRAFÍA VISIONARIO VITALIDAD VIVACIDAD VIVAZ VIVIFICACIÓN VIVIR VIVO VOLUNTAD VOLUNTARIAMENTE VOTO";
        String negativas = "no fome aburrir aburriendo aburrido aburro aburres aburre aburrimos aburris aburren aburris aburria aburrias aburria aburriamos aburriais aburri aburriste aburrio aburristeis aburrieron aburrire aburriras aburrira aburriremos aburriran aburriras aburriria aburrurias aburririamos aburririais aburras aburra aburriere aburrieres aburrieremos aburrieren aburrieres avergonzado avergonzada avergonzados avergonzadas avergüenzo avergüenzas avergüenza avergonzamos avergonzáis avergüenzan avergonzás avergonzaba avergonzabas avergonzaba avergonzábamos avergonzabais avergonzaban avergoncé avergonzabas avergonzaste avergonzó avergonzamos avergonzasteis avergonzaron avergonzaste avergonzaré avergonzarás avergonzará avergonzaremos avergonzaréis avergonzarán avergonzará avergonzaría avergonzarías avergonzaría avergonzaríamos avergonzaríais avergonzarían avergonzarías avergonzare avergonzares avergonzare avergonzáremos avergonzareis avergonzaren avergonzares avergüence avergüences avergüence avergoncemos avergoncéis avergüencen decepciono decepcionas decepciona decepcionamos decepcionáis decepcionan decepcionás decepcionaba decepcionabas decepcionaba decepcionábamos decepcionabais decepcionaban decepcionabas decepcioné decepcionaste decepcionó decepcionamos decepcionasteis decepcionaron decepcionaste decepcionaré decepcionarás decepcionará decepcionaremos decepcionaréis decepcionarán decepcionarás decepcionaría decepcionarías decepcionaría decepcionaríamos decepcionaríais decepcionarían decepcionarías decepcionare decepcionares decepcionare decepcionáremos decepcionareis decepcionaren decepcionares decepciones decepcione decepcione decepcionemos decepcionéis decepcionen desagrado desagradas desagrada desagradamos desagradáis desagradan desagradás desagradaba desagradabas desagradaba desagradábamos desagradabais desagradaban desagradabas desagradé desagradaste desagradó desagradamos desagradasteis desagradaron desagradaste desagradaré desagradarás desagradará desagradaremos desagradaréis desagradarán desagradarás desagradaría desagradarías desagradaría desagradaríamos desagradaríais desagradarían desagradarías desagradare desagradares desagradare desagradáremos desagradareis desagradaren desagradares desagradase desagradases desagradase desagradásemos desagradaseis desagradasen desagradaras penca chanta mal malo mala malito malita decepcionar decepcionante decepcion anticuado antipático arrogante bajo charlatan cínico cobarde debil desagradable descarado desconsiderado deshonesto desleal despreciativo enfermizo enfermo estupido feo grosero grosera idiota frustrado frustrada horrible horroroso horrorosa horrorizado horrorizada humillante incompetente indiferente infiel ingrato inmaduro intolerante lamentable resentido malhumorado patético presumido presuntuoso sombrio espanto espantozo tonta tonto torpe triste";
        String stopwords[] = {"0","1","2","3","4","5","6","7","8","9","_","a","actualmente","acuerdo","adelante","ademas","además","adrede","afirmó","agregó","ahi","ahora","ahí","al","algo","alguna","algunas","alguno","algunos","algún","alli","allí","alrededor","ambos","ampleamos","antano","antaño","ante","anterior","antes","apenas","aproximadamente","aquel","aquella","aquellas","aquello","aquellos","aqui","aquél","aquélla","aquéllas","aquéllos","aquí","arriba","arribaabajo","aseguró","asi","así","atras","aun","aunque","ayer","añadió","aún","b","bajo","bastante","bien","breve","buen","buena","buenas","bueno","buenos","c","cada","casi","cerca","cierta","ciertas","cierto","ciertos","cinco","claro","comentó","como","con","conmigo","conocer","conseguimos","conseguir","considera","consideró","consigo","consigue","consiguen","consigues","contigo","contra","cosas","creo","cual","cuales","cualquier","cuando","cuanta","cuantas","cuanto","cuantos","cuatro","cuenta","cuál","cuáles","cuándo","cuánta","cuántas","cuánto","cuántos","cómo","d","da","dado","dan","dar","de","debajo","debe","deben","debido","decir","dejó","del","delante","demasiado","demás","dentro","deprisa","desde","despacio","despues","después","detras","detrás","dia","dias","dice","dicen","dicho","dieron","diferente","diferentes","dijeron","dijo","dio","donde","dos","durante","día","días","dónde","e","ejemplo","el","ella","ellas","ello","ellos","embargo","empleais","emplean","emplear","empleas","empleo","en","encima","encuentra","enfrente","enseguida","entonces","entre","era","erais","eramos","eran","eras","eres","es","esa","esas","ese","eso","esos","esta","estaba","estabais","estaban","estabas","estad","estada","estadas","estado","estados","estais","estamos","estan","estando","estar","estaremos","estará","estarán","estarás","estaré","estaréis","estaría","estaríais","estaríamos","estarían","estarías","estas","este","estemos","esto","estos","estoy","estuve","estuviera","estuvierais","estuvieran","estuvieras","estuvieron","estuviese","estuvieseis","estuviesen","estuvieses","estuvimos","estuviste","estuvisteis","estuviéramos","estuviésemos","estuvo","está","estábamos","estáis","están","estás","esté","estéis","estén","estés","ex","excepto","existe","existen","explicó","expresó","f","fin","final","fue","fuera","fuerais","fueran","fueras","fueron","fuese","fueseis","fuesen","fueses","fui","fuimos","fuiste","fuisteis","fuéramos","fuésemos","g","general","gran","grandes","gueno","h","ha","haber","habia","habida","habidas","habido","habidos","habiendo","habla","hablan","habremos","habrá","habrán","habrás","habré","habréis","habría","habríais","habríamos","habrían","habrías","habéis","había","habíais","habíamos","habían","habías","hace","haceis","hacemos","hacen","hacer","hacerlo","haces","hacia","haciendo","hago","han","has","hasta","hay","haya","hayamos","hayan","hayas","hayáis","he","hecho","hemos","hicieron","hizo","horas","hoy","hube","hubiera","hubierais","hubieran","hubieras","hubieron","hubiese","hubieseis","hubiesen","hubieses","hubimos","hubiste","hubisteis","hubiéramos","hubiésemos","hubo","i","igual","incluso","indicó","informo","informó","intenta","intentais","intentamos","intentan","intentar","intentas","intento","ir","j","junto","k","l","la","lado","largo","las","le","lejos","les","llegó","lleva","llevar","lo","los","luego","lugar","m","mal","manera","manifestó","mas","mayor","me","mediante","medio","mejor","mencionó","menos","menudo","mi","mia","mias","mientras","mio","mios","mis","misma","mismas","mismo","mismos","modo","momento","mucha","muchas","mucho","muchos","muy","más","mí","mía","mías","mío","míos","n","nada","nadie","ni","ninguna","ningunas","ninguno","ningunos","ningún","no","nos","nosotras","nosotros","nuestra","nuestras","nuestro","nuestros","nueva","nuevas","nuevo","nuevos","nunca","o","ocho","os","otra","otras","otro","otros","p","pais","para","parece","parte","partir","pasada","pasado","paìs","peor","pero","pesar","poca","pocas","poco","pocos","podeis","podemos","poder","podria","podriais","podriamos","podrian","podrias","podrá","podrán","podría","podrían","poner","por","por qué","porque","posible","primer","primera","primero","primeros","principalmente","pronto","propia","propias","propio","propios","proximo","próximo","próximos","pudo","pueda","puede","pueden","puedo","pues","q","qeu","que","quedó","queremos","quien","quienes","quiere","quiza","quizas","quizá","quizás","quién","quiénes","qué","r","raras","realizado","realizar","realizó","repente","respecto","s","sabe","sabeis","sabemos","saben","saber","sabes","sal","salvo","se","sea","seamos","sean","seas","segun","segunda","segundo","según","seis","ser","sera","seremos","será","serán","serás","seré","seréis","sería","seríais","seríamos","serían","serías","seáis","señaló","si","sido","siempre","siendo","siete","sigue","siguiente","sin","sino","sobre","sois","sola","solamente","solas","solo","solos","somos","son","soy","soyos","su","supuesto","sus","suya","suyas","suyo","suyos","sé","sí","sólo","t","tal","tambien","también","tampoco","tan","tanto","tarde","te","temprano","tendremos","tendrá","tendrán","tendrás","tendré","tendréis","tendría","tendríais","tendríamos","tendrían","tendrías","tened","teneis","tenemos","tener","tenga","tengamos","tengan","tengas","tengo","tengáis","tenida","tenidas","tenido","tenidos","teniendo","tenéis","tenía","teníais","teníamos","tenían","tenías","tercera","ti","tiempo","tiene","tienen","tienes","toda","todas","todavia","todavía","todo","todos","total","trabaja","trabajais","trabajamos","trabajan","trabajar","trabajas","trabajo","tras","trata","través","tres","tu","tus","tuve","tuviera","tuvierais","tuvieran","tuvieras","tuvieron","tuviese","tuvieseis","tuviesen","tuvieses","tuvimos","tuviste","tuvisteis","tuviéramos","tuviésemos","tuvo","tuya","tuyas","tuyo","tuyos","tú","u","ultimo","un","una","unas","uno","unos","usa","usais","usamos","usan","usar","usas","uso","usted","ustedes","v","va","vais","valor","vamos","van","varias","varios","vaya","veces","ver","verdad","verdadera","verdadero","vez","vosotras","vosotros","voy","vuestra","vuestras","vuestro","vuestros","w","x","y","ya","yo","z","él","éramos","ésa","ésas","ése","ésos","ésta","éstas","éste","éstos","última","últimas","último","últimos"};
        String tokenPos[] = positivas.toLowerCase().split(" ");
        String tokenPos2[] = positivas2.toLowerCase().split(" ");
        String tokenNeg[] = negativas.split(" ");
        Set<String> setPositivas = new HashSet<String>(Arrays.asList(tokenPos));
        Set<String> setPositivas2 = new HashSet<String>(Arrays.asList(tokenPos2));
        Set<String> setNegativas = new HashSet<String>(Arrays.asList(tokenNeg));
        Set<String> setStopwords = new HashSet<String>(Arrays.asList(stopwords));

        DBCursor cursor = this.collection.find();
        while(cursor.hasNext())
        {
            DBObject document = cursor.next();
            String text = (String) document.get("text");
            String textArr[] = text.toLowerCase().split(" ");
            ArrayList<String> textList = new ArrayList<String>(Arrays.asList(textArr));
            //se remueven las stopwords del tweet
            textList.removeAll(setStopwords);

            //contadores de palabras positivas, negativas y neutrales
            int contPos = 0;
            int contNeg = 0;
            int contNeu = 0;


            for(String compareWord : textList)
            {
                if(setPositivas.contains(compareWord))
                {
                    System.out.println("pos: "+compareWord);
                    contPos++;
                    continue;
                }
                if(setPositivas2.contains(compareWord))
                {
                    contPos++;
                    System.out.println("pos: "+compareWord);
                    continue;
                }
                if(setNegativas.contains(compareWord.toLowerCase()))
                {
                    contNeg++;
                    System.out.println("neg: "+compareWord);
                    continue;
                }
                contNeu++;
            }

            float emote_score = ((float)contPos / 2) + ((float)contNeu / 4) + ((float)contNeg / 8);


            //actualizar documentos
            DBObject updated = new BasicDBObject();
            updated.put("$set", new BasicDBObject("emote_score", emote_score));
            this.collection.update(document, updated);
        }
    }



    public List<TweetModel> search(Festival concierto) {
        List<TweetModel> list = new ArrayList<>();
        String searchString = concierto.getFilters();
        DBCursor cursor = this.collection.find(new BasicDBObject("$text", new BasicDBObject("$search", searchString)));
        while (cursor.hasNext()) {
            DBObject document = cursor.next();
            TweetModel data = new TweetModel();
            data.setText((String) document.get("text"));
            //por alguna razon no castea la fecha como string ni date
            //data.setCreatedAt((Date)document.get("created_at"));
            if(document.get("in_reply_to_status_id") != null)
                data.setInReplyToStatusId((long) document.get("in_reply_to_status_id"));
            if(document.get("in_reply_to_status_id_str") != null)
                data.setInReplyToStatusIdStr((String) document.get("in_reply_to_status_id_str"));
            if((document.get("favorite_count")) != null)
                data.setFavoriteCount((int) document.get("favorite_count"));
            if((document.get("favorited")) != null)
                data.setFavorited((boolean) document.get("favorited"));
            data.setFilterLevel((String) document.get("filter_level"));
            /*//data.setId((long) document.get("id"));
            if(document.get("in_reply_to_screen_name") != null)
                data.setInReplyToScreenName((String) document.get("in_reply_to_screen_name"));
            if(document.get("in_reply_to_user_id") != null)
                data.setInReplyToUserId((long) document.get("in_reply_to_user_id"));
            if(document.get("in_reply_to_user_id_str") != null)
                data.setInReplyToUserIdStr((String) document.get("in_reply_to_user_id_str"));*/
            if(document.get("lang") != null)
                data.setLang((String) document.get("lang"));
            if(document.get("possibly_sensitive") != null)
                data.setPossiblySensitive((boolean) document.get("possibly_sensitive"));
            if(document.get("quoted_status_id") != null)
                data.setQuotedStatusId((long) document.get("quoted_status_id"));
            if(document.get("quoted_status_id_str") != null)
                data.setQuotedStatusIdStr((String) document.get("quoted_status_id_str"));
            //if(document.get("retweet_count") != null)
              //  data.setRetweetCount((int) document.get("retweet_count"));
            data.setEmoteScore((double) document.get("emote_score"));
            data.setRetweeted((boolean) document.get("retweeted"));
            data.setSource((String) document.get("source"));
            data.setTruncated((boolean) document.get("truncated"));
            data.setComuna((String) document.get("geo"));

            list.add(data);
        }

        return list;     
    }

    public List<TweetModel> searchDate(Festival concierto, String fecha){
        List<TweetModel> list = new ArrayList<>();
        String searchString = concierto.getFilters();

        try {
            DateFormat formatter;
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dateInicio = (Date) formatter.parse(fecha);
            Calendar c = Calendar.getInstance();
            c.setTime(formatter.parse(fecha));
            c.add(Calendar.DATE, 1);  // agrega 1 dia a fecha inicio
            Date dateFin = formatter.parse(formatter.format(c.getTime()));  // fechafin = fechainicio + 1 dia
            //Date dateFin = (Date) formatter.parse(fechaInicio);
            //java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());

            //busca todos los tweets entre fecha inicio y fecha fin
            BasicDBObject busqueda = new BasicDBObject("created_at", new BasicDBObject("$gte", dateInicio)
                .append("$lte", dateFin));
            busqueda.append("$text", new BasicDBObject("$search", searchString));
            DBCursor cursor = this.collection.find(busqueda);
            while (cursor.hasNext()) {
                DBObject document = cursor.next();
                TweetModel data = new TweetModel();
                data.setText((String) document.get("text"));
                data.setEmoteScore((Double)(document.get("emote_score")));
                data.setComuna((String) document.get("geo"));
                list.add(data);
            }
            return list;  
        } catch (ParseException e) {
            return list;
        }
    }

    public List<TweetModel> searchConcepto(Festival concierto, String concepto) {
        List<TweetModel> list = new ArrayList<>();
        String searchString = concierto.getFilters();
        concepto = concepto.replace("+"," ");
        BasicDBObject busqueda = new BasicDBObject("$text", new BasicDBObject("$search", searchString));
        busqueda.append("$text", new BasicDBObject("$search", concepto));
        DBCursor cursor = this.collection.find(busqueda);
        while (cursor.hasNext()) {
            DBObject document = cursor.next();
            TweetModel data = new TweetModel();
            data.setText((String) document.get("text"));
            data.setEmoteScore((Double)(document.get("emote_score")));
            data.setComuna((String) document.get("geo"));
            list.add(data);
        }

        return list;     
    }

    public long contarFestival(Festival concierto) {
        List<TweetModel> list = new ArrayList<>();
        String searchString = concierto.getFilters();
        long contar = this.collection.count(new BasicDBObject("$text", new BasicDBObject("$search", searchString)));
        return contar;   
    }

    public void closeConnection() {
        this.mongo.close();
    }

    public static Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
                
        return cal.getTime();
    }

    public DBCollection getCollection() {
        return this.collection;
    }

    public MongoCollection<Document> getMongoCollection() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase("tbd");
        MongoCollection<Document> coleccion = db.getCollection("tweets");
        //mongoClient.close();
        return coleccion;
    }

}
