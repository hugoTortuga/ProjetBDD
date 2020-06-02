package bdd;

import java.io.*;
import java.util.TreeSet;
import java.nio.ByteBuffer;

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
			throw new NullPointerException("L'objet que vous tentez de sérialiser est null");

		try{
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			ObjectOutput output = new ObjectOutputStream(arrayOutputStream);
			output.writeObject(o);
			return arrayOutputStream.toByteArray();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return null;
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
		if(data == null)
			throw new NullPointerException("Le tableau de byte que vous tentez de désérialiser est null");

		try{
			ByteArrayInputStream arrayInput = new ByteArrayInputStream(data);
			ObjectInput input = new ObjectInputStream(arrayInput);
			return (Serializable) input.readObject();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			throw new ClassNotFoundException("Object non reconnu");
		}
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
		if(freeSpaceIntervals == null)
			throw new NullPointerException("Le tableau de byte que vous tentez de sérialiser est null");

		try{
			ByteArrayOutputStream arrayOutput = new ByteArrayOutputStream();
			DataOutputStream output = new DataOutputStream(arrayOutput);
			for (BDD.FreeSpaceInterval value : freeSpaceIntervals) {
				output.writeLong(value.getStartPosition());
				output.writeLong(value.getLength());
			}
			byte[] array = arrayOutput.toByteArray();
			return array;

		}
		catch(Exception ex)
		{
			System.out.println(ex);
			throw new IOException();
		}
	}

	/**
	 * Effectue l'opération inverse de la fonction {@link #serializeFreeSpaceIntervals(TreeSet)}
	 * @param data le tableau binaire
	 * @return le tableau d'espaces libres
	 * @throws IOException si un problème d'entrée/sortie se produit
	 */
	static TreeSet<BDD.FreeSpaceInterval> deserializeFreeSpaceIntervals(byte[] data) throws IOException {
		if(data == null)
			throw new NullPointerException("Le tableau de byte que vous tentez de désérialiser est null");

		try{

			byte[] array1 = new byte[8];
			byte[] array2 = new byte[8];

			ByteArrayInputStream arrayInput = new ByteArrayInputStream(data);
			TreeSet<BDD.FreeSpaceInterval> tree = new TreeSet<BDD.FreeSpaceInterval>();

			while (arrayInput.read(array1) != -1 && arrayInput.read(array2) != -1) {
				tree.add(new BDD.FreeSpaceInterval(
						ByteBuffer.wrap(array1).getLong(),
						ByteBuffer.wrap(array2).getLong()
				));
			}
			return tree;
		}
		catch(Exception ex){
			System.out.println(ex);
			throw new IOException();
		}
	}
}
