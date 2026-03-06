
public class Cards
	{
		String number;
		boolean isValid;

		public Cards(String number, boolean isValid)
			{
				this.number = number;
				this.isValid = isValid;
			}

		public String getNumber()
			{
				return number;
			}

		public void setNumber(String number)
			{
				this.number = number;
			}

		public boolean isValid()
			{
				return isValid;
			}

		public void setValid(boolean isValid)
			{
				this.isValid = isValid;
			}
		
		
	}
