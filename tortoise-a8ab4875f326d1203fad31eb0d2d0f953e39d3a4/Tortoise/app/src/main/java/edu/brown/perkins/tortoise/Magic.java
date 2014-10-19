//import java.security.InvalidAlgorithmParameterException;
//import java.security.InvalidKeyException;
//import java.security.KeyPair;
//import java.security.KeyPairGenerator;
//import java.security.NoSuchAlgorithmException;
//import java.security.NoSuchProviderException;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//import java.security.SecureRandom;
//import java.security.Security;
//
//import javax.crypto.*;
//
//import de.flexiprovider.common.ies.IESParameterSpec;
//import de.flexiprovider.core.FlexiCoreProvider;
//import de.flexiprovider.ec.FlexiECProvider;
//import de.flexiprovider.ec.parameters.CurveParams.*;
//import de.flexiprovider.ec.parameters.CurveParams;
//import de.flexiprovider.ec.parameters.CurveRegistry.*;
//
//public class Magic {
//	public static IESParameterSpec iesParams;
//	public static CurveParams ecParams;
//
//	public Magic () {
//		 Security.addProvider(new FlexiCoreProvider());
//		 Security.addProvider(new FlexiECProvider());
//		 iesParams = new IESParameterSpec("AES128_CBC", "HmacSHA1", null, null);
//		 ecParams = new BrainpoolP512r1();
//	}
//
//	public void set_ecParams(CurveParams ecPara){
//		ecParams = ecPara;
//		return;
//	}
//	// a = curve coeff a; b = curve coeff b; p = prime characteristic; g = basepoint G; r = order r of basepoint; cofactor k
//	public CurveParamsGFP create_ecParams(String a, String b, String p, String g, String r,
//			String k){
//		// need to add id to vector of all possible ids
//		return new CurveParamsGFP(a,b,p,g,r,k);
//
//	}
//
//	//return the correct ecparams from the id
//	public CurveParams get_CurveParams(String oid){return null;};
//
//	public KeyPair genKeys (){
//		try {
//			KeyPairGenerator kpg;
//			kpg = KeyPairGenerator.getInstance("ECIES", "FlexiEC");
//			kpg.initialize(ecParams, new SecureRandom());
//			return kpg.generateKeyPair();
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		} catch (NoSuchProviderException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		} catch (InvalidAlgorithmParameterException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//
//	}
//
//	public PublicKey getPublic(KeyPair kp){
//
//		return kp.getPublic();
//
//	}
//
//	public PrivateKey getPrivate (KeyPair kp){
//
//		return kp.getPrivate();
//
//	}
//	public byte[] encrypt(String s, PublicKey pbk){
//		return encrypt(s.getBytes(),pbk);
//	}
//	public String decrypt_s(byte[] data, PrivateKey prk){
//		return new String(decrypt(data,prk));
//	}
//	public byte[] encrypt(byte[] data, PublicKey pbk){
//		try {
//			Cipher cipher = Cipher.getInstance("ECIES", "FlexiEC");
//			cipher.init(Cipher.ENCRYPT_MODE, pbk, iesParams);
//			return cipher.doFinal(data);
//
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchProviderException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchPaddingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidKeyException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidAlgorithmParameterException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalBlockSizeException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (BadPaddingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public byte[] decrypt(byte[] data, PrivateKey prk){
//		try {
//			Cipher cipher = Cipher.getInstance("ECIES", "FlexiEC");
//			cipher.init(Cipher.DECRYPT_MODE, prk, iesParams);
//			return cipher.doFinal(data);
//
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchProviderException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchPaddingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidKeyException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidAlgorithmParameterException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalBlockSizeException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (BadPaddingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//}