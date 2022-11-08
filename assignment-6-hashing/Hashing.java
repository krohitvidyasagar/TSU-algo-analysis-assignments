import java.util.HashMap;
import java.util.Random;

class Node {
    int value;
    Node next;

    Node(int value) {
        this.value = value;
        this.next = null;
    }
}

class Hashing {

    static int getRandomStudentId() {
        Random rand = new Random();
        return rand.nextInt(1000, 9999);
    }

    static int hashFunction(int num, int size) {
        return num % size;
    }

    static int searchIdUsingHashFunction(int studentId, int size, Node[] hashTable) {
        int counter = 0;
        boolean idFound = false;

        int hashIndex = hashFunction(studentId, size);

        Node travesalNode = hashTable[hashIndex];

        if (travesalNode != null) {
            counter++;
            while(travesalNode != null) {
                if (travesalNode.value == studentId) {
                    idFound = true;
                    break;
                }

                travesalNode = travesalNode.next;
                counter++;
            }

            if (idFound) {
                return counter;
            } else {
                return 0;
            }

        }
        return 0;        
    }


    public static void main(String[] args) {
        int[] studentArray = new int[500];

        HashMap<Integer, Integer> hashSearchCounter = new HashMap<>();
        HashMap<Integer, Integer> linearSearchCounter = new HashMap<>();

        // Generate an array of students with random roll numbers
        for(int i = 0; i < 500; i++) {
            studentArray[i] = Hashing.getRandomStudentId();
        }

        System.out.print("Printing roll numbers of all students: ");
        for(int i = 0; i < 500; i++) {
            System.out.print(studentArray[i] + ", ");
        }

        // Prime number between 128 and 256
        int size = 139;
        Node[] hashTable = new Node[size];

        // Filling the hashtable
        int hashIndex;
        for(int i = 0; i < 500; i++) {
            hashIndex = hashFunction(studentArray[i], size);
            Node tempNode = new Node(studentArray[i]);

            // If the hash index is not found set the tempNode as the first element
            if(hashTable[hashIndex] == null) {
                hashTable[hashIndex] = tempNode;
            } else {
                // If the hash index is found, traverse till the end of the list and then add the new node
                Node traversalNode = hashTable[hashIndex];
                while(traversalNode.next != null) {
                    traversalNode = traversalNode.next;
                }

                traversalNode.next = tempNode;
            }
        }

        System.out.println("\n");
        System.out.println("Displaying the Hashtable Index with all the contents");

        for(int i = 0; i < size; i++) {
            Node temp = hashTable[i];

            if (temp != null) {
                System.out.print(i + "-> \t");
                
                while(temp.next != null) {
                    System.out.print(temp.value + ", ");
                    temp = temp.next;
                }
                System.out.println(temp.value);
            }
        }

        int foundCounter = 0, notFoundCounter = 0;
        
        int[] searchArray = new int[20];
        int searchArrayIndex = 0;

        
        System.out.println("\nSearching the elements using Hash Function");
        while (foundCounter < 17 || notFoundCounter < 3) {
            int searchId = getRandomStudentId();
            
            int counter = searchIdUsingHashFunction(searchId, size, hashTable);

            if(counter == 0 && notFoundCounter < 3) {
                System.out.println("ID: " + searchId + " was not found in hash table with counter: " + counter);
                hashSearchCounter.put(searchId, counter);
                notFoundCounter++;
                
                searchArray[searchArrayIndex] = searchId;
                searchArrayIndex++;

            } else if (counter > 0 && foundCounter < 17) {
                System.out.println("ID: " + searchId + " was found in hash table with counter: " + counter);
                hashSearchCounter.put(searchId, counter);
                foundCounter++;

                searchArray[searchArrayIndex] = searchId;
                searchArrayIndex++;
            }
        }


        System.out.println("\nSearching the elements using Sequential Search Function");
        
        for(int i = 0; i < searchArray.length; i++) {
            int searchId = searchArray[i];
            int counter = 0;
            boolean idFound = false;

            for (int j = 0; j < size; j++) {
                Node traversalNode = hashTable[j];

                while(traversalNode != null) {

                    if(traversalNode.value == searchId) {
                        idFound = true;
                        break;
                    }

                    traversalNode = traversalNode.next;
                    counter++;
                }

                if (idFound) {
                    break;
                }
            }

            if (idFound) {
                System.out.println("ID: " + searchId + " was found in hash table with counter: " + counter);
                linearSearchCounter.put(searchId, counter);
            } else {
                System.out.println("ID: " + searchId + " was not found in hash table with counter: " + counter);
                linearSearchCounter.put(searchId, counter);
            }
        }


        System.out.println("\n\nComputational complexity of Linear and Sequential Search");
        System.out.println("\tNumber  |  Sequential  |  \tHash  ");

        for(int i = 0; i < searchArray.length; i++) {
            System.out.println("\t"+ searchArray[i] + "    |\t" + linearSearchCounter.get(searchArray[i]) + "    |\t" + hashSearchCounter.get(searchArray[i]));
        }
    }
}