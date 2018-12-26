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
