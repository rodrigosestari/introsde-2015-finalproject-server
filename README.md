Final Project

**Introduction to Service Design and Engineering | University of Trento |** 

**Name:** Rodrigo Sestari

**Description:**

In this assignment is implemented a server calling this server. 
The server was deployed on Heroku **http://rodrigo-sestari-final-rest.herokuapp.com/finalprojectrest**.
Instead the client was implemented to call Heroku server. 

This project contains a database management system with internal database MySQL and a Rest WebService.





**Tasks Server:**


* **Request #1:** GET /person should list all the people (see above Person model to know what data to return here) in your database (wrapped under the root element "people")
```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<people>
    <person>
        <idPerson>1</idPerson>
        <firstname>Sonia</firstname>
        <lastname>Broz</lastname>
        <healthProfile>
            <measureType>
                <measure>weight</measure>
                <value>32.0</value>
            </measureType>
            <measureType>
                <measure>heigth</measure>
                <value>37.8</value>
            </measureType>
            <measureType>
                <measure>heart</measure>
                <value>74.0</value>
            </measureType>
        </healthProfile>
    </person>
    <person>
        <idPerson>2</idPerson>
        <firstname>Maicon</firstname>
        <lastname>Antonio</lastname>
        <healthProfile>
            <measureType>
                <measure>heigth</measure>
                <value>102.0</value>
            </measureType>
            <measureType>
                <measure>weight</measure>
                <value>66.9</value>
            </measureType>
            <measureType>
                <measure>heart</measure>
                <value>78.9</value>
            </measureType>
        </healthProfile>
    </person>
    <person>
        <idPerson>3</idPerson>
        <firstname>Anna</firstname>
        <lastname>Caprese</lastname>
        <healthProfile>
            <measureType>
                <measure>weight</measure>
                <value>78.9</value>
            </measureType>
            <measureType>
                <measure>heigth</measure>
                <value>129.8</value>
            </measureType>
            <measureType>
                <measure>heart</measure>
                <value>71.3</value>
            </measureType>
        </healthProfile>
    </person>
</people>
```
* **Request #2:** GET /person/{id} should give all the personal information plus current measures of person identified by {id} (e.g., current measures means current health profile)
```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<person>
    <idPerson>1</idPerson>
    <firstname>Sonia</firstname>
    <lastname>Broz</lastname>
    <healthProfile>
        <measureType>
            <measure>weight</measure>
            <value>32.0</value>
        </measureType>
        <measureType>
            <measure>heigth</measure>
            <value>37.8</value>
        </measureType>
        <measureType>
            <measure>heart</measure>
            <value>74.0</value>
        </measureType>
    </healthProfile>
</person>
```
* **Request #3**: PUT /person/{id} should update the personal information of the person identified by {id} (e.g., only the person's information, not the measures of the health profile)
```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<person>
    <firstname>name</firstname>
    <lastname>surname</lastname>
</person>
```
* **Request #4:** POST /person should create a new person and return the newly created person with its assigned id (if a health profile is included, create also those measurements for the new person).
```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<person>
    <firstname>name</firstname>
    <lastname>new </lastname>
</person>
```
* **Request #5:** DELETE /person/{id} should delete the person identified by {id} from the system
* **Request #6:**  GET /person/{id}/{measureType} should return the list of values (the history) of {measureType} (e.g. weight) for person identified by {id}
```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<measureHistory>
    <measure>
        <mid>4</mid>
        <value>72.0</value>
        <created>2014-11-18</created>
    </measure>
    <measure>
        <mid>5</mid>
        <value>40.3</value>
        <created>2015-02-17</created>
    </measure>
    <measure>
        <mid>7</mid>
        <value>32.0</value>
        <created>2015-02-17</created>
    </measure>
</measureHistory>
```
* **Request #7:** GET /person/{id}/{measureType}/{mid} should return the value of {measureType} (e.g. weight) identified by {mid} for person identified by {id}
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
```
<measureHistory>
    <measure>
        <mid>4</mid>
        <value>72.0</value>
        <created>2014-11-18</created>
    </measure>
</measureHistory>
```
* **Request #8:** POST /person/{id}/{measureType} should save a new value for the {measureType} (e.g. weight) of person identified by {id} and archive the old value in the history
```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    <measure>
        <value>66.0</value>
        <created>2016-11-18</created>
    </measure>
```

* **Request #9:** GET /measureTypes should return the list of measures your model
```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<measureTypes>
    <measureType>weight</measureType>
    <measureType>heigth</measureType>
    <measureType>heart</measureType>
</measureTypes>
```
* **Request #10:** GET /goal/{personId} should return the list of goals by person
```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<goals>
    <goal>
        <idGoal>1</idGoal>
        <start>2014-11-18</start>
        <end>2016-02-24</end>
        <type>MONTHLY</type>
        <signal>LESS_EQUAL</signal>
        <value>70.0</value>
        <measureDefinition>
            <idMeasureDef>1</idMeasureDef>
            <measureName>weight</measureName>
        </measureDefinition>
        <person>
            <idPerson>1</idPerson>
            <firstname>name rrrr</firstname>
            <lastname>surname rrrr</lastname>
        </person>
    </goal>
</goals>
```
* **Request #11:** POST /goal/{personId}/{measure}" save a new goal by user / measure
```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    <goal>
      <start>2010-11-18</start>
        <end>2020-02-24</end>
        <type>MONTHLY</type>
        <signal>LESS</signal>
        <value>100</value>
    </goal>
```

* **Request #12:** GET /goalValuation/{goalId} should return goal validation
```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<goalview>
    <goaltype>
        <idGoal>1</idGoal>
        <start>2014-11-18</start>
        <end>2016-02-24</end>
        <type>MONTHLY</type>
        <signal>LESS_EQUAL</signal>
        <value>70.0</value>
        <measureDefinition>
            <idMeasureDef>1</idMeasureDef>
            <measureName>weight</measureName>
        </measureDefinition>
        <person>
            <idPerson>1</idPerson>
            <firstname>name rrrr</firstname>
            <lastname>surname rrrr</lastname>
        </person>
    </goaltype>
    <goals>
        <expectedValue>70.0</expectedValue>
        <value>72.0</value>
        <result>Fault</result>
        <data>2014-11-01</data>
    </goals>
    <goals>
        <expectedValue>70.0</expectedValue>
        <value>40.3</value>
        <result>OK</result>
        <data>2015-02-01</data>
    </goals>
    <goals>
        <expectedValue>70.0</expectedValue>
        <value>66.0</value>
        <result>OK</result>
        <data>2016-11-01</data>
    </goals>
</goalview>
```


**How to run:**

Running the project requires java and ant.

Ant source file build.xml is annotated. 
* Main targets are: 
* **start:** To start the local server.





**References:**

https://docs.google.com/document/d/1kcRTSzxkvRPT5Lp3A1eqqR51NNqHHHbmHFrjZKqs4Kc/edit#

