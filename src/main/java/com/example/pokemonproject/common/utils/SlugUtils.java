package com.example.pokemonproject.common.utils;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class SlugUtils {

    private static final Pattern NON_ALPHANUMERIC = Pattern.compile("[^a-z0-9\\s-]");
    private static final Pattern MULTIPLE_SPACES_OR_HYPHENS = Pattern.compile("[\\s-]+");

    public static String toSlug(String input) {
        if (input == null || input.isBlank()) {
            return "";
        }

        String normalized = input.toLowerCase(Locale.ROOT).trim();

        // Strip accents (e.g., Flabébé -> flabebe)
        normalized = Normalizer.normalize(normalized, Normalizer.Form.NFD);
        normalized = normalized.replaceAll("\\p{M}", "");

        // Remove all characters that aren't letters, numbers, spaces, or hyphens
        normalized = NON_ALPHANUMERIC.matcher(normalized).replaceAll("");

        // Replace spaces and consecutive hyphens with a single hyphen
        normalized = MULTIPLE_SPACES_OR_HYPHENS.matcher(normalized).replaceAll("-");

        // Clean up any trailing or leading hyphens edge-cases
        return normalized.replaceAll("^-|-$", "");
    }
}