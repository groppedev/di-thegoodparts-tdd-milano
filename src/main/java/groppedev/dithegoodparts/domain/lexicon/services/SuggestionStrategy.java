package groppedev.dithegoodparts.domain.lexicon.services;

import org.apache.commons.collections4.Predicate;

import groppedev.dithegoodparts.domain.Word;

public interface SuggestionStrategy extends Predicate<Word> {}