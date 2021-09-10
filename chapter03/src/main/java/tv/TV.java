package tv;

public class TV {
	private int channel; // 1 ~ 255
	private int volume;  // 0~100
	private boolean power;
	
	public TV() {
		this(1, 0, false);
	}
	
	public TV(int channel, int volum, boolean power) {
		this.channel = channel;
		this.volume = volum;
		this.power = power;
	}
	
	public int getChannel() {
		return channel;
	}

	public int getVolume() {
		return volume;
	}

	public boolean isPower() {
		return power;
	}

	public void power(boolean on) {
		power = on;
	}
	
	public void channel(int channel) {
		if (!power) {
			return;
		}
		
		this.channel = channel;
		
		if (this.channel > 255) {
			this.channel = 1;
		}
		
		if (this.channel < 1) {
			this.channel = 255;
		}
	}
	
	public void channel(boolean up) {
		if (up) {
			channel(channel + 1);
		} else {
			channel(channel - 1);
		}
	}
	
	public void volume(int volume) {
		if (!power) {
			return;
		}
		
		this.volume = volume;
		
		if (this.volume > 100) {
			this.volume = 100;
		}
		
		if (this.volume < 0) {
			this.volume = 0;
		}
	}
	
	public void volume(boolean up) {
		if (up) {
			volume(volume + 1);
		} else {
			volume(volume - 1);
		}
	}
	
	public void status() {
		System.out.println("TV [channel=" + channel + ", volume=" + volume + ", power=" + power + "]");
	}
	
	
}
