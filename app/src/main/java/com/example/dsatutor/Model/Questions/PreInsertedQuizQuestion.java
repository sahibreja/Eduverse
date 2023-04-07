package com.example.dsatutor.Model.Questions;

import com.example.dsatutor.Model.DAO.QuizQuestionDao;
import com.example.dsatutor.Model.ModelClass.QuizQuestion;

import java.util.ArrayList;
import java.util.List;

public class PreInsertedQuizQuestion {

    private List<QuizQuestion> quizQuestionList=new ArrayList<>();
    private QuizQuestionDao quizQuestionDao;

    public PreInsertedQuizQuestion(QuizQuestionDao quizQuestionDao) {
        this.quizQuestionDao = quizQuestionDao;
        AddAllQuestion();
    }

    public void AddAllQuestion()
    {
        //level 1
        quizQuestionList.add(new QuizQuestion(1,"1","Collection of information is","int","float","Data type","Data","Data","Information extracts from data"));
        quizQuestionList.add(new QuizQuestion(1,"1","Organizations of data in main memory is",
                "Data","Data type","Algorithm","Data structure","Data structure","Data structure means structure iof data"));

        quizQuestionList.add(new QuizQuestion(1,"1","What is the definition of an algorithm?","A set of instructions to carry out a task",
                "A way of organizing data with their logical relationship retained","A type of programming language","A method of accessing data","A set of instructions to carry out a task","step by step procedure"));

        quizQuestionList.add(new QuizQuestion(1,"2","What does a data structure represent?","A set of instructions to carry out a task","A way of organizing data with their logical relationship retained",
                "A type of programming language","A method of accessing data","A way of organizing data with their logical relationship retained","Organization"));

        quizQuestionList.add(new QuizQuestion(1,"1","What is the definition of a data structure?","A set of instructions to carry out a task",
                "A way of organizing data with their logical relationship retained","A type of programming language","A method of accessing data","A way of organizing data with their logical relationship retained",""));

        quizQuestionList.add(new QuizQuestion(1,"2","What is the most important aspect of data structure design?","The ability to organize data to suit a specific purpose.", "The ability to encrypt data.","The ability to limit access to data.","The ability to hide information from users.","The ability to organize data to suit a specific purpose.",""));

        quizQuestionList.add(new QuizQuestion(1,"2","How do data structures help humans understand information?","By organizing data in a way that is easy to understand.", "By encrypting data so it can only be accessed by authorized users.","By limiting access to data.","By making data difficult to understand.","By organizing data in a way that is easy to understand.",""));

        quizQuestionList.add(new QuizQuestion(1,"3","What is the relationship between algorithms and data structures?","Algorithm operations are tightly coupled to the data structure's design.", "Algorithms are not related to data structures.","Data structures are only used with certain algorithms.","Algorithms are used to encrypt data.","Algorithm operations are tightly coupled to the data structure's design.",""));

        quizQuestionList.add(new QuizQuestion(1,"3","What information does each data structure contain?","Information about data values, relationships between data, and functions that can be applied to the data.", "Information about user preferences.","Information about data ownership.","Information about hardware components.","Information about data values, relationships between data, and functions that can be applied to the data.",""));

        quizQuestionList.add(new QuizQuestion(1,"2","Why are data structures important in computer science and programming?","They help organize data for use with algorithms.", "They make it difficult for machines to understand data.","They limit access to data.","They are not important in computer science and programming.","They help organize data for use with algorithms.",""));

        quizQuestionList.add(new QuizQuestion(1,"1","What is the purpose of data structures?","To arrange data to suit a specific purpose.", "To make data inaccessible to users.","To make it difficult for machines to understand data.","To hide information from users.","To arrange data to suit a specific purpose.",""));

        quizQuestionList.add(new QuizQuestion(1,"1","What is a data structure?","A specialized format for organizing, processing, retrieving and storing data.", "A tool used to collect data from users.","A software application used to analyze data.","A method for encrypting data.","A specialized format for organizing, processing, retrieving and storing data.",""));

        quizQuestionList.add(new QuizQuestion(1,"2","Which of the following best describes data?","A collection of opinions", "A collection of facts","A collection of beliefs","A collection of assumptions.","A collection of facts",""));

        quizQuestionList.add(new QuizQuestion(1,"3","What type of information can be considered as data?","Images and videos", "Only numbers and measurements","Only descriptions of things","All of these","All of these",""));


        quizQuestionList.add(new QuizQuestion(1,"3","What is the purpose of an algorithm analysis?","To determine the efficiency of an algorithm in terms of time and space complexity.","To determine the correctness of an algorithm.","To determine the complexity of a problem.","To determine procedure","To determine the efficiency of an algorithm in terms of time and space complexity.","Algorithm analysis is the process of determining the efficiency of an algorithm in terms of time and space complexity."));



        //level 2
        quizQuestionList.add(new QuizQuestion(2,"2","Which of the following data structures is used for storing elements in a sorted order?", "Queue","Stack", "Linked List","Tree","Tree", "Think about the structure of a binary search tree."));

        quizQuestionList.add(new QuizQuestion(2,"1","Which of the following data structures is used for storing elements in a FIFO (First-In-First-Out) order?","Queue","Stack","Linked List", "Tree", "Queue","Think about the order in which elements are removed from the data structure."));

        quizQuestionList.add(new QuizQuestion(2,"1","Which of the following data structures is used for storing elements in a LIFO (Last-In-First-Out) order?","Queue","Stack","Linked List","Tree","Stack","Think about the order in which elements are removed from the data structure."));

        quizQuestionList.add(new QuizQuestion(2,"1","Which data type is considered as a primitive data type?", "Array","String","Boolean","None of the above","Boolean","A primitive data type is a data type that is built into the programming language and is not composed of other data types."));

        quizQuestionList.add(new QuizQuestion(2,"2","Which data structure can store multiple values of different data types?","Array","Stack","Queue","Struct","Struct","A struct is a non-primitive data structure that can store multiple values of different data types."));

        quizQuestionList.add(new QuizQuestion(2,"2","Which data structure can store a hierarchical collection of elements?", "Queue","Tree","Set","Graph","Tree","A tree is a non-primitive data structure that can store a hierarchical collection of elements."));

        quizQuestionList.add(new QuizQuestion(2,"1","Which of the following data structures is used for implementing a stack?", "Queue", "Stack","Linked List","Tree","Stack","Think about the LIFO property of a stack."));

        quizQuestionList.add(new QuizQuestion(2,"2","Which of the following data structures is used for implementing a priority queue?","Queue", "Stack" , "Heap", "Tree" , "Heap","Think about the structure of a binary heap."));

        quizQuestionList.add(new QuizQuestion(2,"2","Which of the following data structures is used for implementing a hash table?","Queue","Stack", "Linked List", "Array","Array","Think about the underlying structure of a hash table."));

        quizQuestionList.add(new QuizQuestion(2,"1","Which data type is considered as a non-primitive data type?","Integer","Character","String","Array","String","A non-primitive data type is a type that is not defined by the programming language itself, but is instead created by the programmer."));

        quizQuestionList.add(new QuizQuestion(2,"2","Which data structure uses dynamic memory allocation?", "Array", "Linked List", "Stack", "Queue", "Linked List", "Linked List data structure uses dynamic memory allocation to add or remove elements at runtime."));

        quizQuestionList.add(new QuizQuestion(2,"3","Which data structure is linear in nature?", "Tree","Graph","Queue","Map","Queue","A linear data structure is a data structure where data elements are organized sequentially or linearly, like in Queue."));

        quizQuestionList.add(new QuizQuestion(2,"3","Which data structure is used to implement a graph?","Array","Linked list", "Queue", "Adjacency list or matrix","Adjacency list or matrix","A graph can be implemented using an adjacency list or matrix, with each node in the graph represented as a vertex in the list or matrix."));

        quizQuestionList.add(new QuizQuestion(2,"2","Which data structure is nonlinear in nature?", "Stack","Array", "Linked List", "Tree","Tree", "A nonlinear data structure is a data structure where data elements are not organized sequentially or linearly, like in Tree."));

        quizQuestionList.add(new QuizQuestion(2,"1","Which data structure allows you to insert and delete elements at both ends?","Queue","Stack","Linked List","Array","Linked List","A data structure which stores address of next node."));


        //level 3
        quizQuestionList.add(new QuizQuestion(3,"1","Which of the following is an advantage of using data structures?","Simplifies the implementation of algorithms","Makes code more complex","Increases program speed","Reduces memory usage","Simplifies the implementation of algorithms","Data structures simplify the implementation of algorithms by providing efficient ways to organize and manipulate data."));

        quizQuestionList.add(new QuizQuestion(3,"2","Why is understanding data structure important?","It enables efficient algorithm design and implementation","It allows for more complex and convoluted code","It simplifies debugging and error handling","It makes coding more interesting","It enables efficient algorithm design and implementation","Understanding data structures is crucial for designing and implementing efficient algorithms for various applications."));

        quizQuestionList.add(new QuizQuestion(3,"3","Which of the following is an example of the importance of data structure in real-world applications?","Sorting an array of numbers in ascending order","Writing a simple Hello, World! program","Printing a multiplication table","Searching for a specific record in a database","Searching for a specific record in a database","Data structures are crucial for efficient searching and retrieval of data in real-world applications, such as databases and search engines."));

        quizQuestionList.add(new QuizQuestion(3,"3","Which of the following is an example of a data structure that is important for efficient sorting algorithms?","Linked lists","Hash tables","Trees","Stacks","Trees","Trees are commonly used for efficient sorting algorithms, such as binary search trees and AVL trees."));

        quizQuestionList.add(new QuizQuestion(3, "2","Why is choosing the appropriate data structure important?","It ensures faster program execution.","It makes code more complex.","It reduces the likelihood of memory leaks and crashes.","It enables more efficient error handling.","It ensures faster program execution.","Choosing the appropriate data structure can significantly impact the performance of programs, enabling faster execution and better resource management."));

        quizQuestionList.add(new QuizQuestion(3,"2","Which of the following is an advantage of using algorithms in data structures?","Improved data storage efficiency.","Simplified data retrieval and modification.","Reduced memory usage.","No option is correct.","Simplified data retrieval and modification.","Algorithms can help to simplify data retrieval and modification, making it easier to work with data structures."));

        quizQuestionList.add(new QuizQuestion(3,"1","Which of the following is an example of how algorithms can be used in day-to-day life?","Online shopping recommendations based on past purchases.","Building a computer from scratch.","Solving complex mathematical equations.","No option is correct.","Online shopping recommendations based on past purchases.","Algorithms can be used to personalize online shopping recommendations based on past purchases, making it easier to find products that meet your needs."));

        quizQuestionList.add(new QuizQuestion(3,"1","Which of the following is a disadvantage of using algorithms?","Algorithms can be difficult to implement.","Algorithms are always accurate and error-free.","Algorithms are not useful in problem-solving.","No option is correct.","Algorithms can be difficult to implement.","Implementing algorithms can be challenging and require significant time and resources."));

        quizQuestionList.add(new QuizQuestion(3,"3","What could happen if data structures are not used in coding?","Code would be simpler and easier to read.","Code would be more efficient.","Code would be more error-prone and difficult to maintain.","No option is correct.","Code would be more error-prone and difficult to maintain.","Without data structures, code can become more complex and difficult to maintain, making it prone to errors and bugs."));

        quizQuestionList.add(new QuizQuestion(3, "2","Which of the following is a disadvantage of not using data structures?","Reduced memory usage.","Improved code optimization.","Increased programming speed.","No option is correct.","No option is correct.","Data structures are important for organizing and manipulating data in code, so not using them can result in a lack of structure and organization."));



        //level 4
        quizQuestionList.add(new QuizQuestion(4,"1","What is a data item in data structure?","A collection of related data elements.","A single unit of data.","A way to store data in a hierarchical structure.","No option is correct.","A single unit of data.","A data item is a single unit of data that can be accessed and manipulated in a program."));

        quizQuestionList.add(new QuizQuestion(4,"2","What is a group item in data structure?","A collection of related data elements.","A single unit of data.","A way to store data in a hierarchical structure.","No option is correct.","A collection of related data elements.","A group item is a collection of related data elements that are treated as a single unit in a program."));

        quizQuestionList.add(new QuizQuestion(4,"1","What is an entity in data structure?","A collection of related data elements.","A single unit of data.","A way to store data in a hierarchical structure.","No option is correct.","A collection of related data elements.","An entity is a collection of related data elements that are treated as a single unit in a program and may represent a real-world object or concept."));

        quizQuestionList.add(new QuizQuestion(4,"3","What is data in data structure?","A collection of related data elements.","A single unit of data.","A way to store data in a hierarchical structure.","No option is correct.","A collection of related data elements.","Data refers to the information that is stored, processed, and manipulated in a program."));

        quizQuestionList.add(new QuizQuestion(4,"3","Which of the following is an example of a data item?","A customer's name.","A list of customers.","A customer's order history.","No option is correct.","A customer's name.","A customer's name is a single unit of data that can be accessed and manipulated in a program, making it an example of a data item."));

        quizQuestionList.add(new QuizQuestion(4,"2","Which of the following terms refers to the process of allocating and freeing up memory in a computer system?","Data structure","Algorithm","Memory management","Input/output","Memory management","Memory management refers to the process of allocating and freeing up memory in a computer system to optimize performance and prevent errors."));

        quizQuestionList.add(new QuizQuestion(4,"2","What is an array?","A way of organizing data in a computer","A program that performs calculations","A way of communicating with other programs","A way of storing data in a database","A way of organizing data in a computer","Think about how arrays are used to store a collection of data elements of the same type."));

        quizQuestionList.add(new QuizQuestion(4,"2","What is a stack?","A data structure that follows the Last In First Out (LIFO) principle","A data structure that follows the First In First Out (FIFO) principle","A data structure used for efficient searching and sorting","A data structure used for storing elements in sorted order","A data structure that follows the Last In First Out (LIFO) principle","Think about how a stack works and how it follows the LIFO principle."));

        quizQuestionList.add(new QuizQuestion(4,"2","What is a queue?","A data structure that follows the First In First Out (FIFO) principle","A data structure that follows the Last In First Out (LIFO) principle","A data structure used for efficient searching and sorting","A data structure used for storing elements in sorted order","A data structure that follows the First In First Out (FIFO) principle","Think about how a queue works and how it follows the FIFO principle."));

        quizQuestionList.add(new QuizQuestion(4,"1","What is a linked list?","A data structure that stores elements in a linear sequence","A data structure that uses a hash function to store and retrieve values","A data structure that stores elements in a tree-like structure","A data structure that stores elements in a matrix-like structure","A data structure that stores elements in a linear sequence","Think about how a linked list is structured and how it is used to store data elements in a linear sequence."));

        quizQuestionList.add(new QuizQuestion(4,"3","What is a binary tree?","A tree-like data structure where each node has at most two children","A linked list-like data structure where each node has a pointer to the previous and next nodes","A matrix-like data structure where each element is accessed by a row and column index","A hash table-like data structure where values are stored based on a key-value pair","A tree-like data structure where each node has at most two children","Think about how a binary tree is structured and how it is used to store data elements in a tree-like structure."));

        quizQuestionList.add(new QuizQuestion(4,"1","What is a pointer?","A data type used to store numbers","A device used to store data","An object that holds data in a linked list","A variable that stores the memory address of another variable","A variable that stores the memory address of another variable","Think about how pointers are used to reference and manipulate data stored in memory."));




    }
    public void insertInDatabase()
    {
        if(quizQuestionDao.getCount()==0)
        {
            for (int i=0;i< quizQuestionList.size();i++)
            {
                quizQuestionDao.setQuestion(quizQuestionList.get(i));
            }
        }
    }
}
