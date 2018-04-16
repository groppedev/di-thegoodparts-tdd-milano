package groppedev.dithegoodparts.noioc;

import groppedev.dithegoodparts.domain.Word;
import groppedev.dithegoodparts.domain.lexicon.Lexicon;

public class SpellCheckerNoIoC
{
	// Si utilizza il lessico dei cibi italiani.
	private final Lexicon lexicon = LexiconFactory.getInstance().newItalianFoodLexicon();
	
	public boolean check(Word wordToCheck)
	{
		return lexicon.hasWord(wordToCheck);
	}
}
