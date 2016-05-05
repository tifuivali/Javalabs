
package rsa;
import java.util.*;
import java.math.*;
import java.math.BigInteger;
import iaik.utils.*;



/**
 *
 * @author tifui
 */
 class PublicKey
{
    public BigInteger e;
    public  BigInteger n;
    public PublicKey(BigInteger e,BigInteger n)
    {
        this.e=e;
        this.n=n;
    }
    public void write()
    {
        System.err.println("Public key:");
        System.err.println("e:"+e.toString()+" n="+n.toString());
    }
}

class PrivateKey
{
    public  BigInteger d;
    public PrivateKey(BigInteger d)
    {
        this.d=d;
    }
      public  void write()
    {
        System.err.println("Private key:");
        System.err.println("d:"+d.toString());
    }
}
      class PerecheAlfaBeta
      {
          public BigInteger a;
          public BigInteger b;
          public PerecheAlfaBeta(BigInteger a,BigInteger b)
          {
              this.a=a;
              this.b=b;
          }
          public void write()
          {
              System.err.println(" alfa="+a.toString()+" beta"+b.toString());
          }
      }

public class RSA {

    static public BigInteger  p,q,n,phi,d,e,M,C,decriptat_M;
    static Random random;
    static private PrivateKey privateKey;
    static PublicKey publicKey;
    static public Scanner scan;
    static BigInteger alfai,betai;
    
    
    
    
    
    public static void main(String[] args) {
     
        
        random=new Random();
        scan=new Scanner(System.in);
        //se selecteaza p si q prime
        BigInteger a=new BigInteger(1024, 100, random);
        BigInteger b=new BigInteger(1024, 100, random);
        p=NumberTheory.nextPrime(a);
        q=NumberTheory.nextPrime(b);
        
        //calculam n=p*q
        n=p.multiply(q);
        
        //calcaulam indicatorul lui euler phi
        phi= indicatorulEuler();
        
        //se calcauleaza e (un nr intre 1 si phi a.i cmmdc(e,phi)=1
        e=getE();
        
        //se calcauleaza d
        d=e.modInverse(phi);
        
        //afizez cheile
        publicKey=new PublicKey(e, n);
        privateKey=new PrivateKey(d);
        publicKey.write();
        privateKey.write();
        System.out.println("p="+p.toString());
        System.err.println("q="+q.toString());
        System.err.println("n="+n.toString());
        System.err.println("phi="+phi.toString());
        System.out.println("e="+e);
        System.out.println("Introduceti numarul pt criptare(M):");
        M=scan.nextBigInteger();
        if(M.compareTo(n)!=-1)
        {
            System.out.println("M trebuie sa fie mai mic ca n!");
            return;
        }
        
        //criptam numarul M in C
        C=M.modPow(e, n);
        System.err.println("Textul cifrat C este:"+C.toString());
        
        //numarul decriptat
        decriptat_M=C.modPow(d,n);
        System.err.println("Textul C decriptat este:"+decriptat_M.toString());
        RSA rsa=new RSA();
        //decriptare folosind TCR
        rsa.decriptare_TCR();
       
        
    }
    
    
   public static BigInteger getE()
   {
       BigInteger b;
       BigInteger intial=new BigInteger("2");
       BigInteger increment=new BigInteger("1");
       BigInteger E=intial;      
       b=intial;
        while(!b.equals(phi))
        {
            //daca cel mai mare divizor comun dintre b si phi este 1
          if(b.gcd(phi).intValue()==1)
          {
              E=b;
              return E;
          }
          b=b.add(increment);
        }
        return E;
   }
    
   static public BigInteger indicatorulEuler()
   {
      BigInteger a,b,res;
      a=p.subtract(new BigInteger("1"));
      b=q.subtract(new BigInteger("1"));
      res=a.multiply(b);
      return res;
      
   }

   public void decriptare_TCR()
   {
        BigInteger d1=this.d.mod(this.p.subtract(BigInteger.ONE));
        BigInteger d2=this.d.mod(this.q.subtract(BigInteger.ONE));
	BigInteger x1=this.C.modPow(d1, this.p);
	BigInteger x2=this.C.modPow(d2, this.q);
        BigInteger plainT=x1.add(this.p.multiply(x2.subtract(x1).multiply(this.p.modInverse(this.q)).mod(this.q)));
        System.out.println("Nr decriptat prin TCR este "+plainT);
   }
   
   
   
   static public  BigInteger getQ(BigInteger a,BigInteger b)
   {
       BigInteger q=BigInteger.ZERO;
       while(q.multiply(b).compareTo(a)==-1||q.multiply(b).compareTo(a)==0)
       {
           q=q.add(BigInteger.ONE);
       }
       return q.subtract(BigInteger.ONE);
   }
   
   public static List<BigInteger> getFractieContiunua(BigInteger a,BigInteger b,int index)
   {
       List<BigInteger> fractie=new ArrayList<>();
       BigInteger r,r1,r2;
       r=BigInteger.ONE;
       
       fractie.add(getQ(a, b));
       r2=a.subtract(fractie.get(0).multiply(b));
       if(index==0)
           return fractie;
       fractie.add(getQ(b, r2));
       r1=b.subtract(fractie.get(1).multiply(r2));
       if(index==1)
           return fractie;
       int i=2;
       while(i<=index&&r.compareTo(BigInteger.ZERO)!=0)
       {
           fractie.add(getQ(r2,r1));
           BigInteger p=fractie.get(i).multiply(r1);
           r=r2.subtract(p);
           r2=r1;
           r1=r;
           i++;
           
       }
       
       return fractie;
   }
   
   public List<PerecheAlfaBeta> getPerechiAlfabeta_i(BigInteger a,BigInteger b,int index)
   {
       List<BigInteger> q=getFractieContiunua(a, b, index);
       List<PerecheAlfaBeta> list =new ArrayList();
       list.add(new PerecheAlfaBeta(BigInteger.ZERO, BigInteger.ZERO));
       BigInteger l1,l2,b1,b2,li,bi;
       l2=q.get(0);
       b2=BigInteger.ONE;
       list.add(new PerecheAlfaBeta(l2, b2));
       if(index==1)
       {
           
           return list;
       }
       l1=q.get(0).multiply(q.get(1)).add(BigInteger.ONE);
       b1=q.get(1);
       list.add(new PerecheAlfaBeta(l1, b1));
       if(index==2)
       {
           
           return list;
       }
       
       int i=2;
       while(i<=index&&i<q.size())
       {
           li=q.get(i-1).multiply(l1).add(l2);
           bi=q.get(i-1).multiply(b1).add(b2);
           list.add(new PerecheAlfaBeta(li, bi));
           l2=l1;
           b2=b1;
           l1=li;
           b1=bi;
           i++;
       }
       return list;
       
   }
   
   
    
  
}
    

