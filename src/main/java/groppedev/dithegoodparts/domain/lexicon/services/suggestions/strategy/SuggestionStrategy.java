package groppedev.dithegoodparts.domain.lexicon.services.suggestions.strategy;

import org.apache.commons.collections4.Predicate;

import groppedev.dithegoodparts.domain.Word;

public interface SuggestionStrategy extends Predicate<Word> {}