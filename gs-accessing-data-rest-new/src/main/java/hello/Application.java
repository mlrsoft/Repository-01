package hello;

/**
 * Updated............: LEH 2019-07-16 07:45
 *
 * Using Postman
 * 
 * GET http://localhost:8080
 * Result Body:
{
    "_links": {
        "people": {
            "href": "http://localhost:8080/people{?page,size,sort}",
            "templated": true
        },
        "profile": {
            "href": "http://localhost:8080/profile"
        }
    }
}
 *
 * GET http://localhost:8080/profile
 * Result Body:
{
    "_links": {
        "self": {
            "href": "http://localhost:8080/profile"
        },
        "people": {
            "href": "http://localhost:8080/profile/people"
        }
    }
}
 *
 * GET http://localhost:8080/profile/people
{
    "alps": {
        "version": "1.0",
        "descriptor": [
            {
                "id": "person-representation",
                "href": "http://localhost:8080/profile/people",
                "descriptor": [
                    {
                        "name": "firstName",
                        "type": "SEMANTIC"
                    },
                    {
                        "name": "lastName",
                        "type": "SEMANTIC"
                    }
                ]
            },
            {
                "id": "create-people",
                "name": "people",
                "type": "UNSAFE",
                "rt": "#person-representation"
            },
            {
                "id": "get-people",
                "name": "people",
                "type": "SAFE",
                "descriptor": [
                    {
                        "name": "page",
                        "type": "SEMANTIC",
                        "doc": {
                            "format": "TEXT",
                            "value": "The page to return."
                        }
                    },
                    {
                        "name": "size",
                        "type": "SEMANTIC",
                        "doc": {
                            "format": "TEXT",
                            "value": "The size of the page to return."
                        }
                    },
                    {
                        "name": "sort",
                        "type": "SEMANTIC",
                        "doc": {
                            "format": "TEXT",
                            "value": "The sorting criteria to use to calculate the content of the page."
                        }
                    }
                ],
                "rt": "#person-representation"
            },
            {
                "id": "patch-person",
                "name": "person",
                "type": "UNSAFE",
                "rt": "#person-representation"
            },
            {
                "id": "delete-person",
                "name": "person",
                "type": "IDEMPOTENT",
                "rt": "#person-representation"
            },
            {
                "id": "update-person",
                "name": "person",
                "type": "IDEMPOTENT",
                "rt": "#person-representation"
            },
            {
                "id": "get-person",
                "name": "person",
                "type": "SAFE",
                "rt": "#person-representation"
            },
            {
                "name": "findByLastName",
                "type": "SAFE",
                "descriptor": [
                    {
                        "name": "name",
                        "type": "SEMANTIC"
                    }
                ]
            }
        ]
    }
}
 *
 * Add people data
 * POST http://localhost:8080/people
 * Headers:
 *   Key=Content-Type, Value=application/json
 * Body:
 *  Raw 
{
    "firstName": "Frodo",
    "lastName": "Baggins"
}
 * Result Body:
{
    "firstName": "Frodo",
    "lastName": "Baggins",
    "_links": {
        "self": {
            "href": "http://localhost:8080/people/1"
        },
        "person": {
            "href": "http://localhost:8080/people/1"
        }
    }
}
 * 
 * Get specific people data
 * GET http://localhost:8080/people/1
 * Result Body:
{
    "firstName": "Frodo",
    "lastName": "Baggins",
    "_links": {
        "self": {
            "href": "http://localhost:8080/people/1"
        },
        "person": {
            "href": "http://localhost:8080/people/1"
        }
    }
}
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

   public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
   }
}
