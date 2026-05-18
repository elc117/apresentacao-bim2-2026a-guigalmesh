import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

class RandomTeamMateGenerator{
    private static final String API_URL = "https://randomuser.me/api/?results=1&nat=BR";

    public static ArrayList<TeamMate> generateTeamMates(int count){
        ArrayList<TeamMate> teamMates = new ArrayList<>();
        Random random = new Random();

        for(int i = 0; i < count; i++){
            String jsonResponse = fetchJson(API_URL);
            String name = parseJsonName(jsonResponse);

            String id = String.valueOf(1000 + i);
            Boolean online = random.nextBoolean();
            teamMates.add(new TeamMate(id, name, online));
        }

        return teamMates;
    }

    private static String fetchJson(String apiUrl) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error fetching data: " + e.getMessage());
        }
        return response.toString();
    }

    private static String parseJsonName(String json) {
        // Very basic and not robust JSON parsing
        // Do not try this at home :-)
        // For better JSON parsing, see org.json package
        String name = "Unknown";
        int startIndex = json.indexOf("\"name\":") + 8; // Adjusted to skip to the name object
        if (startIndex != -1) {
            int firstNameIndex = json.indexOf("\"first\"", startIndex) + 9; // Skip to first name
            int firstNameEnd = json.indexOf("\"", firstNameIndex);
            int lastNameIndex = json.indexOf("\"last\"", firstNameEnd) + 8; // Skip to last name
            int lastNameEnd = json.indexOf("\"", lastNameIndex);
            String firstName = json.substring(firstNameIndex, firstNameEnd);
            String lastName = json.substring(lastNameIndex, lastNameEnd);
            name = firstName + " " + lastName;
        }
        return name;
    }
}

class ListOfTeamMates{
    public static void main(String[] args){
        ArrayList<TeamMate> teamMates = RandomTeamMateGenerator.generateTeamMates(10);
        System.out.println("Nome dos TeamMate\n");
        for(int i = 0; i < teamMates.size(); i++){
            TeamMate mate = teamMates.get(i);
            System.out.println("Nome: " + mate.getName());
        }

        System.out.println("\n\nId dos TeamMate que estão Online: \n");
        for(int i = 0; i < teamMates.size(); i++){
            TeamMate mate = teamMates.get(i);
            if(mate.getOnline()){
                System.out.println("ID: " + mate.getId());
            }
        }

        System.out.println("\n\nTodos os TeamMates\n");
        for(int i = 0; i < teamMates.size(); i++){
            TeamMate mate = teamMates.get(i);
            System.out.println("Nome: " + mate.getName() + " ID: " + mate.getId() + " Online: " + mate.getOnline());
        }

        System.out.println("\n\nRemovendo TeamMates que estão offline com o método removeIf\n");
        teamMates.removeIf(mate -> mate.getOnline() == false);

        System.out.println("\nApenas TeamMates online\n");
        for(int i = 0; i < teamMates.size(); i++){
            TeamMate mate = teamMates.get(i);
            System.out.println("Nome: " + mate.getName() + " ID: " + mate.getId() + " Online: " + mate.getOnline());
        }
    }


}


