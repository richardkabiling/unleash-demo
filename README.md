# Unleash Demo
1. `./gradlew clean build && docker compose up --build`
2. You may access the Unleash console via `localhost:8081` in your browser
3. The feature flag name used by the different endpoints is `greeting.enable-punctuations`.
4. The endpoints available are:
   1. `GET localhost:8080/greeting/simple`
   2. `GET localhost:8080/greeting/proxy`
   3. `GET localhost:8080/greeting/variant`
   
All have the same behavior which returns with punctuation field if enabled. For the variant endpoint, variants must be configured on the feature flag. It will use the string payload as punctuation instead.