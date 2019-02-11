package designchallenge1;

public interface SubjectInterface
{
   public void register (ObserverInterface e) ;
   public void unregister (ObserverInterface e);
   public void notifyObserver();

}
