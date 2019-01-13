public class Pirate {
	private Position p;
	
	Pirate(Position p){
		p.setObject(Pirate.class);
		this.p = p;
	}
	
	public void setPosition(Position p){
		p.setObject(Pirate.class);
		this.p = p;
	}
	
	public Position getPosition() {
		return this.p;
	}
	
	public void moveToPlayer(Position playerPosition) {
		int[] arr = new int[8]; //up right down left
		arr[0]=getDistanceToPlayer(this.p.getX()-1,this.p.getY()-1,playerPosition);
		arr[1]=getDistanceToPlayer(this.p.getX()-1,this.p.getY(), playerPosition);
		arr[2]=getDistanceToPlayer(this.p.getX()-1, this.p.getY()+1, playerPosition);
		arr[3]=getDistanceToPlayer(this.p.getX(),this.p.getY()+1, playerPosition);
		arr[4]=getDistanceToPlayer(this.p.getX()+1,this.p.getY()+1, playerPosition);
		arr[5]=getDistanceToPlayer(this.p.getX()+1,this.p.getY(), playerPosition);
		arr[6]=getDistanceToPlayer(this.p.getX()+1,this.p.getY()-1, playerPosition);
		arr[7]=getDistanceToPlayer(this.p.getX(),this.p.getY()-1, playerPosition);
		int min=arr[0];
		int indeks = 0;
		for(int i=1;i<8;i++) {
			if(arr[i]<min) {
				min=arr[i];
				indeks = i;
			}
		}
		switch(indeks) {
			case 0:
				this.p.UP_LEFT();
				break;
			case 1:
				this.p.UP();
				break;
			case 2:
				this.p.UP_RIGHT();
				break;
			case 3:
				this.p.RIGHT();
				break;
			case 4:
				this.p.DOWN_RIGHT();
				break;
			case 5:
				this.p.DOWN();
				break;
			case 6:
				this.p.DOWN_LEFT();
				break;
			case 7:
				this.p.LEFT();
				break;
			default:
				break;
		}
	}
	
	public void moveToPlayer(Position playerPosition,int l) {
		int[] arr = new int[4]; //up right down left
		arr[0]=getDistanceToPlayer(this.p.getX()-1,this.p.getY(), playerPosition);
		arr[1]=getDistanceToPlayer(this.p.getX(),this.p.getY()+1, playerPosition);
		arr[2]=getDistanceToPlayer(this.p.getX()+1,this.p.getY(), playerPosition);
		arr[3]=getDistanceToPlayer(this.p.getX(),this.p.getY()-1, playerPosition);	
		int min=arr[0];
		int indeks = 0;
		for(int i=0;i<4;i++) {
			if(arr[i]<min) {
				min=arr[i];
				indeks = i;
			}
		}
		switch(indeks) {
			case 0:
				this.p.UP();
				break;
			case 1:
				this.p.RIGHT();
				break;
			case 2:
				this.p.DOWN();
				break;
			case 3:
				this.p.LEFT();
				break;
		}
	}
	
	private int getDistanceToPlayer(int x,int y,Position playerPosition) {
		return Math.abs(playerPosition.getX()-x)+Math.abs(playerPosition.getY()-y);
	}
}
