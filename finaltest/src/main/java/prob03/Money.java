package prob03;

import java.util.Objects;

public class Money {
	private int amount;
	
	/* 코드 작성 */
	public Money(int num) {
		this.amount = num;
	}
	
	public int getAmount() {
		return amount;
	}

	public Money add(Money money) {
		return new Money(amount + money.getAmount());
	}
	
	public Money minus(Money money) {
		return new Money(amount - money.getAmount());
	}
	
	public Money multiply(Money money) {
		return new Money(amount * money.getAmount());
	}
	
	public Money devide(Money money) {
		return new Money(amount / money.getAmount());
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		return amount == other.amount;
	}
	
	
	
	
}
