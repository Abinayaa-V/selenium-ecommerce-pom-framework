package automation.ecommerce.data;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import automation.ecommerce.models.User;
import automation.ecommerce.utils.DataReader;

public class TestDataProvider {
	
	@DataProvider(name = "userData")
    public Object[][] getUserData() throws Exception {

        List<Map<String, String>> data =
                DataReader.getJsonDataToMap("users.json");

        Object[][] result = new Object[data.size()][1];

        for (int i = 0; i < data.size(); i++) {

            Map<String, String> userData = data.get(i);

            User user = new User(
                    userData.get("firstName"),
                    userData.get("lastName"),
                    userData.get("email"),
                    userData.get("password"),
                    userData.get("address"),
                    userData.get("country"),
                    userData.get("state"),
                    userData.get("city"),
                    userData.get("zipcode"),
                    userData.get("mobile")
            );

            result[i][0] = user;
        }

        return result;
    }
}
