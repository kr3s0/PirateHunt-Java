/**
 * Class which describes our Barrier object. Every Barrier created as instance 
 * of this class has private attribute p of Position.class type.
 * This,rather small class,has only 3 methods. Constructor and pair of Getter and Setter.
 * Since barriers are not part of "movable" game object in our case, there is no real need
 * for developing this class any further.
 * 
 * @author Tarik Kreso - kr3s0
 * @since 2018-12-22
 */
public class Barrier {
	 private Position p;
	 
	 Barrier(Position t){
		 t.setObject(Barrier.class);
		 this.p=t;
	 }
	 
	 public void setPosition(Position p) {
		 p.setObject(Barrier.class);
		 this.p=p;
	 }
	 
	 public Position getPosition() {
		 return this.p;
	 }
	
}
