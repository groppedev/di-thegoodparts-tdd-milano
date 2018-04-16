package groppedev.dithegoodparts.domain.lexicon.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import groppedev.dithegoodparts.domain.Word;
import groppedev.dithegoodparts.domain.lexicon.LexiconType;

public class LexiconFileRepository implements LexiconRepository, AutoCloseable
{
	private final Set<LexiconWord> lexiconWords;

	public LexiconFileRepository() throws IOException 
	{
		lexiconWords = new HashSet<LexiconWord>();
		init();
	}
	
	private void init() throws IOException
	{
		for(LexiconType type : LexiconType.values())
		{
			try(BufferedReader reader = Files.newBufferedReader(resolveResourceName(type)))
			{
				String line;
				while((line = reader.readLine()) != null)
				{
					lexiconWords.add(new LexiconWord(type, Word.aWord(line)));
				}
			}
		}
	}

	@Override
	public void close()
	{
		System.out.println("Distrutto il repository del lessico [Implementazione su file]");
	}

	private Path resolveResourceName(LexiconType type) 
	{
		return Paths.get("").toAbsolutePath().resolve("src/main/resources").resolve(type.name() + ".lexicon");
	}
	
	@Override
	public Collection<Word> words(LexiconType type)
	{
		return LexiconUtils.toWord(type, lexiconWords);
	}
}
