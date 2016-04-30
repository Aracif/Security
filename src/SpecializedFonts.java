

import java.awt.Font;

/**
 * 
 * Creating the font for this project
 *
 */
public class SpecializedFonts {
	private static Font categoryFont;

	/**
	 * Styling up the font for this project
	 * @return a new font that bold
	 */
	public static Font makeBoldText() {
		categoryFont = new Font("Monospaced", Font.BOLD, 14);
		return categoryFont;
	}

}
