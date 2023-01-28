public class Person implements Comparable {
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    //Getter
    public String getFirstName() {
        return firstName;
    }
    //Setter
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    //Порівнює 2 об'єкта
    @Override
    public int compareTo(Object o) {
        if(o instanceof Person)
        {
            Person p = (Person)o;
            //Сортує по прізвищу
            int result = this.lastName.compareTo(p.lastName);
            //Сортує по імені, якщо у об'єктів однакові прізвища
            if(result==0)
                result=this.firstName.compareTo(p.firstName);
            return result;
        }
        return 0;
    }
}
