package v1;

import java.awt.Font;

public class SpecializedFonts {
	private static Font categoryFont;
	
	
	public static Font makeBoldText(){
		categoryFont = new Font("Monospaced", Font.BOLD, 14);
		return categoryFont;
	}

}
