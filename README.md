# prime-cinema
An Android app that displays top rated movies according to user selected genre and release year. Uses Retrofit and Gson libraries to make API calls and deserialize returned JSON objects.

This project is meant to serve as a personal introduction to the Activity Lifecycle, Fragments, Intents, RecyclerViews, and other Android development topics.

To-Do:
- Fragments providing movie details upon click/ tap
- Remove cards with null response from OMDB call
- Exception handling for network requests
- Spinners to select genre and year (replace temporary randomized genres/ years)
- ProgressBar for loading data transition (perhaps using RxJava library)
- Improve UI (color theme, formatting, font size, etc.)
- Review and address warnings
- Add tab for tv shows

Advice:
- Place RecyclerView into a fragment and then transition to movie details fragment onclick
- Only display information from API1 call on CardView, and then provide details from API1 & API2 onclick.