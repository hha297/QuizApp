# Mobile Final Project Instructions

## 1. Clone the Repository

- Open Android Studio.
- Choose "Get from VCS (Version Control)".
- Paste this URL: [https://github.com/hha297/QuizApp](https://github.com/hha297/QuizApp).

## 2. Install XAMPP

- Install XAMPP.
- Turn on MySQL and Apache services.

## 3. Configure Database

- Access [localhost/phpmyadmin](http://localhost/phpmyadmin).
- Create a new database, name it 'quiz_db' (or a name of your choice).
- Create a new table (e.g., 'math_quiz' or 'science_quiz') with the following columns:
  - `Id` – INT – check on A_I (Auto Increment)
  - `Question` – VARCHAR – 200
  - `Option1`, `Option2`, `Option3`, `Option4` – VARCHAR – 200
  - `Correct_option` – VARCHAR – 200
- Click save.

## 4. Insert Data

- Insert some data following the database structure.

## 5. Create PHP API

- Navigate to `/your_xampp_folder/htdocs/` and create a new folder named 'quiz'.
- In the 'quiz' folder, create a text document named 'question_api' and paste the following code:

```php
<?php

// Create connection
$connect = mysqli_connect("localhost", "root", "", "quiz_db");
$stmt = $connect->prepare("SELECT `question`, `option1`, `option2`, `option3`, `option4`, `correct_option` FROM `math_quiz`");

// If you have more than one table, update the $stmt: $stmt = $connect->prepare("SELECT `question`, `option1`, `option2`, `option3`, `option4`, `correct_option` FROM `math_quiz` UNION ALL SELECT `question`, `option1`, `option2`, `option3`, `option4`, `correct_option` FROM `other_quiz`");


// Execute query
$stmt->execute();

// Binding the results to the query
$stmt->bind_result($question, $option1, $option2, $option3, $option4, $correct_option);

// Create an empty array 
$questions_array = array();

// Traversing through all the questions
while($stmt->fetch()) {
    $temp = array();
    $temp['question'] = $question;
    $temp['option1'] = $option1;
    $temp['option2'] = $option2;
    $temp['option3'] = $option3;   
    $temp['option4'] = $option4;   
    $temp['correct_option'] = $correct_option;

    array_push($questions_array, $temp);
}

// Display the result in JSON format:
echo json_encode($questions_array);

?>
```

- Save this file as 'question_api.php'



## Now you can create more data and run the app


**Note:**

- Ensure your XAMPP server is running, and the PHP API file (`question_api.php`) is accessible at [localhost/quiz/question_api.php](http://localhost/quiz/question_api.php).

- In your Android Studio project, make sure to update the URL in your code to match the location of your PHP API.

- Adjust the database connection details in the PHP file (`question_api.php`) if needed.

- If you encounter any issues, check the PHP error logs for debugging.

---


