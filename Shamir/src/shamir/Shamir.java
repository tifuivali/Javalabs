/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shamir;
import java.math.BigInteger;
import java.util.*;
import iaik.utils.*;
import jdk.nashorn.internal.objects.NativeArray;


/**
 *
 * @author tifui
 */
 class Pereche
{
    int i;
    BigInteger secret;
}

public class Shamir {

    
    
    Random rand=new Random();
    static int k=0;
    static int n=0;
    public static BigInteger S;
    
   boolean isPrime(int n) {
    //check if n is a multiple of 2
    if (n%2==0) return false;
    //if not, then just check the odds
    for(int i=3;i*i<=n;i+=2) {
        if(n%i==0)
            return false;
    }
    return true;
}
    public int getP()
    {
       int x=0;
        do{
            x=rand.nextInt(100);
        }
        while(x<4);
            
        while(!isPrime(x))
            x++;
        return x;
         
    }
    public int generateSecret(int p)
    {
        return rand.nextInt(p);
    }
    public int[] generateKCoefficients(int p,int k,int secret)
    {
       int[] coeff=new int[k];
       //calculam coeficientii k-1...k1 ,k0 va fi secretul
       for(int i=k-1;i>=1;i--)
       {
           //generam coeficientul in Zp
           int x=rand.nextInt(p);
           if(x==0)
               x++;
           coeff[i]=x;
       }
       // coeficientul liber ,k0 este secretul
       coeff[0]=secret;
       return coeff;
    }
    
    public BigInteger[] getSubsecrets(int n,int k,int[] coeff,int p)
    {
        BigInteger p_big=BigInteger.valueOf((long)p);
        BigInteger[] subsecrets=new BigInteger[n+1];
        //calculam n subsecrete I1...In
        for(int i=1;i<=n;i++)
        {
            BigInteger currentSubSecret=new BigInteger("0");
            //subsecretul curent Ii =P(xi) , xi=i => Ii=P(i)
            for(int j=k-1;j>=1;j--)
            {
                //pentru fiecare coeficient k-1..k1
                //calculam termenulCurent=(i^j(puterea curenta)mod p)*coeficientul_curent mod p
                BigInteger x_i=BigInteger.valueOf((long)i);
                BigInteger currCoef=BigInteger.valueOf((long)coeff[j]);
                BigInteger currentTerm=x_i.pow(j).multiply(currCoef);
                currentSubSecret=currentSubSecret.add(currentTerm);
            }  
            
            currentSubSecret=currentSubSecret.add(BigInteger.valueOf((long)coeff[0]));
            currentSubSecret=currentSubSecret.mod(p_big);
            subsecrets[i]=currentSubSecret;
        }
        return subsecrets;
    
    }
    
     public BigInteger getSecret(int n,int k,int p,Pereche[] subsecretPairs)
        {
            BigInteger secret=new BigInteger("0");
            BigInteger p_B=BigInteger.valueOf((long)p);
        for (Pereche subsecretPair : subsecretPairs)
        {
            int i = subsecretPair.i;
            BigInteger I_i = subsecretPair.secret;
            BigInteger curentTerm=I_i.multiply(produs(i, p, subsecretPairs)).mod(p_B);
            secret=secret.add(curentTerm).mod(p_B);
        }
          
         
            return secret;
        }
       
     static BigInteger produs(int i,int p,Pereche[] subSecretPairs)
     {
         BigInteger produs=BigInteger.ONE;
         BigInteger p_B=BigInteger.valueOf((long)p);
        for (Pereche subSecretPair : subSecretPairs) {
            int j = subSecretPair.i;
            BigInteger j_B=BigInteger.valueOf((long)j);
            if(j!=i)
            {
                BigInteger numitor=j_B.subtract(BigInteger.valueOf((long)i));
                if(numitor.compareTo(BigInteger.ZERO)==-1)
                    numitor=numitor.add(p_B);
                BigInteger fractie=j_B.multiply(numitor.modInverse(p_B)).mod(p_B);
                produs=produs.multiply(fractie).mod(p_B);
            }
        }
         return produs;
         
     }
     
            
    
    public static void main(String[] args) 
    {
        Scanner scan=new Scanner(System.in);
        System.err.println("Introduceti k:");
        k=scan.nextInt();
        System.err.println("Introduceti n:");
        n=scan.nextInt();
        Shamir sham=new Shamir();
        
        //se genereaza un numar prim (<100)
        int p=sham.getP();
        //se genereaza secretul S
        int secret=sham.generateSecret(p);
         S=BigInteger.valueOf((long)secret);
        System.err.println("secret:"+secret);
        //se genereaaza lista cu coeficienti a pilinomului de grad k-1 
        //coeficientul liber este secretul
        int[] coeficienti=sham.generateKCoefficients(p, k,secret);
        System.err.println("p="+p);
        for(int i=k-1;i>=0;i--)
        {
            System.err.print(coeficienti[i]+" ");
        }
        System.err.println();
        
        //se calculeaza subsecretele I1 ...In
        BigInteger[] subsecrets=sham.getSubsecrets(n, k, coeficienti, p);
        //afisam subsecret
        for(int i=1;i<=n;i++)
        {
            System.err.println("Subsecret "+i+":"+subsecrets[i].toString());
        }
        
        Pereche[] subSecretPairs;
        subSecretPairs = new Pereche[k];
        System.err.println("k="+k+"..trebuie sa intrdouceti perechile i:secret");
        for(int i=0;i<k;i++)
        {
            Pereche pair=new Pereche();
            System.err.println((i+1)+"i=");
            pair.i=scan.nextInt();
            System.err.println("secret:");
            pair.secret=scan.nextBigInteger();
            subSecretPairs[i]=pair;
        }
        //se reconstitue secretul
        BigInteger secretReconstituit=sham.getSecret(n, k, p, subSecretPairs);
        System.err.println("Secret reconstruit:"+secretReconstituit.toString());
  
    }
    
    
    
}
