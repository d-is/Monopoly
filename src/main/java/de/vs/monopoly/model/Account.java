package de.vs.monopoly.model;

public class Account {
	
		private Player player;
		private int saldo;
		
		public Player getPlayer() {
			return player;
		}

		public void setPlayer(Player player) {
			this.player = player;
		}

		public int getSaldo() {
			return saldo;
		}

		public void setSaldo(int saldo) {
			this.saldo = saldo;
		}

		public Account (Player player, int saldo){
			this.player = player;
			this.saldo = saldo;
		}
		
		public void AddAmount(int amount){
			this.saldo += amount;
		}
		
		public void SubtractAmount(int amount){
			this.saldo -= amount;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((player == null) ? 0 : player.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Account other = (Account) obj;
			if (player == null) {
				if (other.player != null)
					return false;
			} else if (!player.equals(other.player))
				return false;
			return true;
		}
}
