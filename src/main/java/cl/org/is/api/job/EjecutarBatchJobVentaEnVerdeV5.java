/**
 *@name CopiarArchivosCuadraturaJob2.java
 * 
 *@version 1.0 
 * 
 *@date 30-03-2017
 * 
 *@author EA7129
 * 
 *@copyright Cencosud. All rights reserved.
 */
package cl.org.is.api.job;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
//import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @description
 */
public class EjecutarBatchJobVentaEnVerdeV5 {

	private static BufferedWriter bw;
	private static String path;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, String> mapArguments = new HashMap<String, String>();
		String sKeyAux = null;

		for (int i = 0; i < args.length; i++) {

			if (i % 2 == 0) {

				sKeyAux = args[i];
			} else {

				mapArguments.put(sKeyAux, args[i]);
			}
		}

		try {

			File info = null;
			File miDir = new File(".");
			path = miDir.getCanonicalPath();
			// path = "C:\\PROYECTOS\\C8INVENTARIOS\\MASIVOS\\";
			// info = new File(path+"/LOG/info.txt");
			info = new File(path + "/info.txt");
			bw = new BufferedWriter(new FileWriter(info));
			info("El programa se esta ejecutando...");
			crearTxt(mapArguments);
			System.out.println("El programa finalizo.");
			info("El programa finalizo.");
			bw.close();
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
	}

	private static void crearTxt(Map<String, String> mapArguments) {
		// TODO Auto-generated method stub
		Connection dbconnection = crearConexionOracle2();
		Connection dbconnOracle = crearConexionOracle2();
		Connection dbconnOracle2 = crearConexionOracle2();
		//Connection dbconnPostgresql = crearPostgresql();
		
		
		File file1              							= null;
		int iFechaIni           							= 0;
		int iFechaFin          								= 0;
		String iFechaIni2           							= null;
		String iFechaFin2          								= null;
		
		
		BufferedWriter bw       							= null;
		
		PreparedStatement pstmt 							= null;
		
		PreparedStatement pstmtInsert 						= null;

		StringBuffer sb        								= null;
		
		//Date now3 = new Date();
		//SimpleDateFormat ft3 = new SimpleDateFormat ("YYYYMMdd");
		//String iFechaFin = ft3.format(now3);
		
		
		//Date nowPro = new Date();
		//SimpleDateFormat ftPro = new SimpleDateFormat ("YYYY-MM-dd HH:mm:ss");
		//String currentDatePro = ftPro.format(nowPro);
		
		
		//String fechaEom = String.valueOf(restarDia(currentDate3));
		//String fechaEom2 = String.valueOf((currentDate3));
		
		//System.out.println("currentDatePro="+currentDatePro);
		//System.out.println("=1="+fechaEom.substring(6,8)+"-"+fechaEom.substring(4,6)+"-"+fechaEom.substring(0,4));
		//System.out.println("=2="+fechaEom2.substring(6,8)+"-"+fechaEom2.substring(4,6)+"-"+fechaEom2.substring(0,4));
		//System.out.println("=2="+fechaEom2.substring(6,8)+"/"+fechaEom2.substring(4,6)+"/"+fechaEom2.substring(2,4));

		try {

			try {
				iFechaIni = restarDia(mapArguments.get("-fi"));
				iFechaFin = restarDia(mapArguments.get("-fi"));

				iFechaIni2 = restarDias2(mapArguments.get("-fi"), 1);
				iFechaFin2 = restarDias2(mapArguments.get("-fi"), 0);
				
				
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			//System.out.println("currentDate1: " + iFechaFin);
			//System.out.println("currentDate2: " + restarDia(iFechaFin));
			info("[iFechaIni] " +iFechaIni);
			info("[iFechaFin] " +iFechaFin);
			
			info("[iFechaIni2] " +iFechaIni2);
			info("[iFechaFin2] " +iFechaFin2);
			
			//Thread.sleep(60);
			//System.out.println("Pausa para Insertar  HORARIO_VTAV_ACT sleep(60 seg)");						
			//agregarRegistroProceso(dbconnOracle2, currentDatePro, "1");
			
			//Thread.sleep(60);
			//System.out.println("Pausa para Insertar  HORARIO_VTAV_ACT_STOCK sleep(60 seg)");						
			//agregarRegistroProcesoStock(dbconnOracle2, currentDatePro, "1");
			
			
			
			Thread.sleep(60);
			System.out.println("Pausa para Eliminar cuadratura_venta_verde sleep(60 seg)");	
			info("Pausa para Eliminar cuadratura_venta_verde sleep(60 seg)");
			//elimnarCuadratura(dbconnOracle2,"DELETE FROM  ECOMMERCE_SOPORTE_VENTA where 1 = 1 AND FECTRANTSL >= '"+iFechaIni+"'  AND FECTRANTSL <= '"+iFechaIni+"'");
			elimnarCuadratura(dbconnOracle2,"DELETE FROM  cuadratura_venta_verde where 1 = 1 AND FECHA_CREACION_ORDEN >= '"+iFechaIni2+" 00:00:00'  AND FECHA_CREACION_ORDEN <= '"+iFechaFin2+" 23:59:59' AND TIPO_RELACION = 5"); // 1 = detalleOcEom
			commit(dbconnOracle2,"COMMIT");
			
			file1        									= new File(path + "/SoporteVentav5-" + iFechaIni + ".txt");
			
			
			
			sb 												= new StringBuffer();
			
			
			
			//##################### CARGAR TABLA ECOMMERCE SOPORTE VENTA  #####################
			Thread.sleep(60);
			System.out.println("Pausa para Insertar  FECHA_CREACION_ORDEN sleep(60 seg)");
			info("Pausa para Insertar FECHA_CREACION_ORDEN sleep(60 seg)");
			
			bw           = new BufferedWriter(new FileWriter(file1));

				
			bw.write("SOLICITUDORIGINAL;");
			bw.write("N_ORDEN_DISTRIBU;");
			bw.write("FECHA_CREACION_ORDEN;");
			bw.write("PONUMB;");
			bw.write("EX14ERROR;");
			bw.write("DESPA;");
			bw.write("TIPO_ESTADO;");
			bw.write("TIPO_RELACION;");
			bw.write("RELNUM;");
			bw.write("INUMBR;");
			bw.write("RELBL5; \n");
			
			
			String sql = "Insert into CUADRATURA_VENTA_VERDE (SOLICITUDORIGINAL,N_ORDEN_DISTRIBU,FECHA_CREACION_ORDEN,PONUMB,EX14ERROR,DESPA,TIPO_ESTADO,TIPO_RELACION, RELNUM, INUMBR, RELBL5, LOGENCONTRADOENJDA) values (?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmtInsert = dbconnOracle2.prepareStatement(sql);
			
			sb = new StringBuffer();
			
			sb.append(	" SELECT  RELNUM,POMUMB,INUMBR,RELBL5 from cuadratura_oc_vtav_comp   where 1 = 1 and FECHA_INGRESO >= '"+iFechaIni+"' AND FECHA_INGRESO <= '"+iFechaFin+"' group by RELNUM,POMUMB,INUMBR,RELBL5 Having sum(CANTIDAD_COMP1)< max(CANTIDAD_COMP2) " );
				
				
			info("[sb]"+sb);
			
			pstmt        = dbconnection.prepareStatement(sb.toString());
			//pstmt.setInt(1, iFechaIni);
			//pstmt.setInt(2, iFechaFin);
			ResultSet rs = pstmt.executeQuery();
			
			info("[Ini paso 6]");
			while (rs.next()) {
					bw.write(""+";");
					bw.write(""+";");
					bw.write(""+";");
					bw.write(rs.getString("PONUMB")+";");
					bw.write(""+";");
					bw.write(""+";");
					bw.write("0"+";");
					bw.write("5"+";");
					bw.write(rs.getString("RELNUM")+";");
					bw.write(rs.getString("INUMBR")+";");
					bw.write(rs.getString("RELBL5")+";");
					bw.write("-"+"\n");

					
					pstmtInsert.setString(1, "");
					pstmtInsert.setString(2, "");
					pstmtInsert.setString(3, "");
					pstmtInsert.setString(4, rs.getString("PONUMB"));
					pstmtInsert.setString(5, "");
					pstmtInsert.setString(6, "");
					pstmtInsert.setInt(7, 0);
					pstmtInsert.setInt(8, 5);
					pstmtInsert.setString(9, rs.getString("RELNUM"));
					pstmtInsert.setString(10, rs.getString("INUMBR"));
					pstmtInsert.setString(11, rs.getString("RELBL5"));
					pstmtInsert.setString(12, "-");
					
					
					pstmtInsert.executeUpdate();
					commit(dbconnOracle2,"COMMIT");
					
				
			
			}
			
			Thread.sleep(60);
			System.out.println("Pausa para Eliminar CUADRATURA_JPDNOVRET sleep(60 seg)");						
			info("Pausa para Eliminar CUADRATURA_JPDNOVRET sleep(60 seg)");
			//##################### CARGAR TABLA CUADRATURA_ECOMMERCE  #####################
			
			
			///###########################	CARGAR TABLA CUADRATURA_JPDTOTDAD	###########################
						


			info("Archivos creados.");
		}
		catch (Exception e) {

			System.out.println(e.getMessage());
			info("[crearTxt1]Exception:"+e.getMessage());
		}
		finally {

			cerrarTodo(dbconnOracle,pstmt,bw);
		}
		
	}

	
	/**
	 * Metodo que elimina un archivo dada una ruta especifica
	 * 
	 * @param String,  ruta especifica el archivo
	 * @param Connection,  Objeto que representa una conexion a la base de datos
	 * @return 
	 * 
	 */
	public static void eliminarArchivo(String archivo) {

		File fichero = new File(archivo);

		if (fichero.delete()) {

			System.out.println("archivo eliminado");

		} else {
			System.out.println("archivo no eliminado");
		}

	}

	
	
	

	/**
	 * Metodo que crea la conexion a la base de datos a KPI
	 * 
	 * @param Connection,  Objeto que representa una conexion a la base de datos
	 * @return 
	 * 
	 */
	private static Connection crearConexionOracle2() {

		Connection dbconnection = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Comentado por cambio de base de datos. El servidor g500603svcr9
			// corresponde shareplex.
			// dbconnection =
			// DriverManager.getConnection("jdbc:oracle:thin:@g500603svcr9:1521:MEOMCLP","REPORTER","RptCyber2015");

			// El servidor g500603sv0zt corresponde a Producción.
			dbconnection = DriverManager.getConnection("jdbc:oracle:thin:@172.18.163.15:1521/XE", "kpiweb", "kpiweb");
		} catch (Exception e) {

			e.printStackTrace();
		}
		return dbconnection;
	}
	
	
	/**
	 * Metodo que cierra la conexion, Procedimintos,  BufferedWriter
	 * 
	 * @param Connection,  Objeto que representa una conexion a la base de datos
	 * @param PreparedStatement, Objeto que representa una instrucción SQL precompilada. 
	 * @return retorna
	 * 
	 */
	private static void cerrarTodo(Connection cnn, PreparedStatement pstmt, BufferedWriter bw) {

		try {

			if (cnn != null) {

				cnn.close();
				cnn = null;
			}
		} catch (Exception e) {

			System.out.println(e.getMessage());
			info("[cerrarTodo]Exception:" + e.getMessage());
		}
		try {

			if (pstmt != null) {

				pstmt.close();
				pstmt = null;
			}
		} catch (Exception e) {

			System.out.println(e.getMessage());
			info("[cerrarTodo]Exception:" + e.getMessage());
		}
		try {

			if (bw != null) {

				bw.flush();
				bw.close();
				bw = null;
			}
		} catch (Exception e) {

			System.out.println(e.getMessage());
			info("[cerrarTodo]Exception:" + e.getMessage());
		}
	}

	/**
	 * Metodo que muestra informacion 
	 * 
	 * @param String, texto a mostra
	 * @param String, cantidad para restar dias
	 * @return String retorna los dias a restar
	 * 
	 */
	private static void info(String texto) {

		try {

			bw.write(texto + "\n");
			bw.flush();
		} catch (Exception e) {

			System.out.println("Exception:" + e.getMessage());
		}
	}

	/**
	 * Metodo que resta dias 
	 * 
	 * @param String, dia que se resta
	 * @param String, cantidad para restar dias
	 * @return String retorna los dias a restar
	 * 
	 */
	private static int restarDia(String sDia) {

		int dia = 0;
		String sFormato = "yyyyMMdd";
		Calendar diaAux = null;
		String sDiaAux = null;
		SimpleDateFormat df = null;

		try {

			diaAux = Calendar.getInstance();
			df = new SimpleDateFormat(sFormato);
			diaAux.setTime(df.parse(sDia));
			diaAux.add(Calendar.DAY_OF_MONTH, -1);
			sDiaAux = df.format(diaAux.getTime());
			dia = Integer.parseInt(sDiaAux);
		} catch (Exception e) {

			info("[restarDia]Exception:" + e.getMessage());
		}
		return dia;
	}
	
	/**
	 * Metodo que resta dias 
	 * 
	 * @param String, dia que se resta
	 * @param String, cantidad para restar dias
	 * @return String retorna los dias a restar
	 * 
	 */
	private static String restarDias2(String sDia, int iCantDias) {

		String sFormatoIn = "yyyyMMdd";
		String sFormatoOut = "yyyy-MM-dd";
		Calendar diaAux = null;
		String sDiaAux = null;
		SimpleDateFormat df = null;

		try {

			diaAux = Calendar.getInstance();
			df = new SimpleDateFormat(sFormatoIn);
			diaAux.setTime(df.parse(sDia));
			diaAux.add(Calendar.DAY_OF_MONTH, -iCantDias);
			df.applyPattern(sFormatoOut);
			sDiaAux = df.format(diaAux.getTime());
		}
		catch (Exception e) {

			info("[restarDias]error: " + e);
		}
		return sDiaAux;
	}
	
	
	

	/**
	 * Metodo que hace commit en la base de datos
	 * 
	 * @param Connection,
	 *            conexion a la base de datos
	 * @return si valor de retorno
	 */

	private static void commit(Connection dbconnection, String sql) {
		PreparedStatement pstmt = null;
		try {
			pstmt = dbconnection.prepareStatement(sql);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cerrarTodo(null, pstmt, null);
		}
	}

	/**
	 * Metodo que ejecuta la eliminacion de registros en una tabla
	 * 
	 * @param Connection,
	 *            conexion de las base de datos
	 * @param String,
	 *            query para la eliminacion
	 * @return
	 */

	private static void elimnarCuadratura(Connection dbconnection, String sql) {
		PreparedStatement pstmt = null;
		try {
			pstmt = dbconnection.prepareStatement(sql);
			System.out.println("registros elimnados : " + pstmt.executeUpdate());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cerrarTodo(null, pstmt, null);
		}

	}

	/**
	 * Metodo que da un formato de fecha
	 * 
	 * @param String, fecha para formatear  
	 * @return 
	 */
	public static String getFormatDay(String fecha) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		// String dateInString = "01/11/2014";

		try {

			Date date = formatter.parse(fecha);
			// System.out.println(date);
			// System.out.println(formatter.format(date));
			return formatter.format(date);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;

	}

}

