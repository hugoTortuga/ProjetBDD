package bdd;

import java.io.*;
import java.util.TreeSet;

/**
 * Classe qui contient des outils de sérialization
 *
 * @author Jason Mahdjoub
 * @version 1.0
 */
class SerializationTools {
	/**
	 * Serialise/binarise l'objet passé en paramètre pour retourner un tableau binaire
	 * @param o l'objet à serialiser
	 * @return the tableau binaire
	 * @throws IOException si un problème d'entrée/sortie se produit
	 */
	static byte[] serialize(Serializable o) throws IOException {
		if(o == null)
			throw new IOException("L'objet que vous tentez de sérialiser est null");
		ByteArrayOutputStream byteArray = null;
		ObjectOutputStream object = null;
		try{
			byteArray = new ByteArrayOutputStream();
			object = new ObjectOutputStream(byteArray);
			object.writeObject(o);
			object.flush();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		finally {
			if(byteArray != null){
				byteArray.close();
			}
			if(object != null){
				object.close();
			}
			return byteArray.toByteArray();
		}
	}

	/**
	 * Désérialise le tableau binaire donné en paramètre pour retrouver l'objet initial avant sa sérialisation
	 * @param data le tableau binaire
	 * @return l'objet désérialisé
	 * @throws IOException si un problème d'entrée/sortie se produit
	 * @throws ClassNotFoundException si un problème lors de la déserialisation s'est produit
	 */
	static Serializable deserialize(byte[] data) throws IOException, ClassNotFoundException {

		/*
			var deserializedObject = new List<StockageAdresseCegid>();
			var ms = new MemoryStream(Encoding.UTF8.GetBytes(json));
			var ser = new DataContractJsonSerializer(deserializedObject.GetType());
			deserializedObject = ser.ReadObject(ms) as List<StockageAdresseCegid>;
			ms.Close();
			*/

		return null;
	}

	/**
	 * Serialise/binarise le tableau d'espaces libres passé en paramètre pour retourner un tableau binaire, mais selon le schéma suivant :
	 * Pour chaque interval ;
	 * <ul>
	 *     <li>écrire en binaire la position de l'interval</li>
	 *     <li>écrire en binaire la taille de l'interval</li>
	 * </ul>
	 * Utilisation pour cela la classe {@link DataOutputStream}
	 *
	 * @param freeSpaceIntervals le tableau d'espaces libres
	 * @return un tableau binaire
	 * @throws IOException si un problème d'entrée/sortie se produit
	 */
	static byte[] serializeFreeSpaceIntervals(TreeSet<BDD.FreeSpaceInterval> freeSpaceIntervals) throws IOException {
		//TODO complete
		return null;
	}

	/**
	 * Effectue l'opération inverse de la fonction {@link #serializeFreeSpaceIntervals(TreeSet)}
	 * @param data le tableau binaire
	 * @return le tableau d'espaces libres
	 * @throws IOException si un problème d'entrée/sortie se produit
	 */
	static TreeSet<BDD.FreeSpaceInterval> deserializeFreeSpaceIntervals(byte[] data) throws IOException {
		//TODO complete
		return null;
	}
}
