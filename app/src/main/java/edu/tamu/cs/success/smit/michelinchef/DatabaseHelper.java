package edu.tamu.cs.success.smit.michelinchef;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smit on 4/28/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MichelinCook.db";
    public SQLiteDatabase db;
    // Table Cooking_Category
    public static final String COOKING_CATEGORY_TABLE_NAME = "Cooking_Category";
    public static final String COOKING_CATEGORY_COL_1 = "cook_id";
    public static final String COOKING_CATEGORY_COL_2 = "name";

    public static final String COOKING_CATEGORY_CREATE_QUERY = "Create table "
            + COOKING_CATEGORY_TABLE_NAME + " ( "
            + COOKING_CATEGORY_COL_1 +" integer PRIMARY KEY autoincrement, "
            + COOKING_CATEGORY_COL_2 + " text not null )";

    // Table Regional_Category
    public static final String REGIONAL_CATEGORY_TABLE_NAME = "Regional_Category";
    public static final String REGIONAL_CATEGORY_COL_1 = "region_id";
    public static final String REGIONAL_CATEGORY_COL_2 = "name";

    public static final String REGIONAL_CATEGORY_CREATE_QUERY = "Create table "
            + REGIONAL_CATEGORY_TABLE_NAME + " ( "
            + REGIONAL_CATEGORY_COL_1 +" integer PRIMARY KEY autoincrement, "
            + REGIONAL_CATEGORY_COL_2 + " text not null )";

    // Table Other_Category
    public static final String OTHER_CATEGORY_TABLE_NAME = "Other_Category";
    public static final String OTHER_CATEGORY_COL_1 = "other_id";
    public static final String OTHER_CATEGORY_COL_2 = "name";

    public static final String OTHER_CATEGORY_CREATE_QUERY = "Create table "
            + OTHER_CATEGORY_TABLE_NAME + " ( "
            + OTHER_CATEGORY_COL_1 +" integer PRIMARY KEY autoincrement, "
            + OTHER_CATEGORY_COL_2 + " text not null )";

    // Table Ingredients
    public static final String INGREDIENTS_TABLE_NAME = "Ingredients";
    public static final String INGREDIENTS_COL_1 = "ingredient_id";
    public static final String INGREDIENTS_COL_2 = "name";
    public static final String INGREDIENTS_COL_3 = "unit";

    public static final String INGREDIENTS_CREATE_QUERY = "Create table "
            + INGREDIENTS_TABLE_NAME + " ( "
            + INGREDIENTS_COL_1 +" integer PRIMARY KEY autoincrement, "
            + INGREDIENTS_COL_2 + " text not null, "
            + INGREDIENTS_COL_3 + " text )";

    // Table Recipe
    public static final String RECIPE_TABLE_NAME = "Recipe";
    public static final String RECIPE_MASTER_TABLE_NAME = "Recipe_Master";
    public static final String RECIPE_COL_1 = "recipe_id";
    public static final String RECIPE_COL_2 = "name";
    public static final String RECIPE_COL_3 = "step_no";
    public static final String RECIPE_COL_4 = "step";
    public static final String RECIPE_COL_5 = "step_time";
    public static final String RECIPE_COL_6 = "image_path";

    public static final String RECIPE_CREATE_QUERY = "Create table "
            + RECIPE_TABLE_NAME + " ( "
            + RECIPE_COL_1 + " integer REFERENCES "
                    +RECIPE_MASTER_TABLE_NAME + "( " +RECIPE_COL_1 + " ),"
            + RECIPE_COL_3 + " integer, "
            + RECIPE_COL_4 + " text not null, "
            + RECIPE_COL_5 + " real not null, "
            + RECIPE_COL_6 + " text, PRIMARY KEY("+ RECIPE_COL_1 + ", " + RECIPE_COL_3 + "))";

    public static final String RECIPE_MASTER_CREATE_QUERY = "Create table "
            + RECIPE_MASTER_TABLE_NAME + " ( "
            + RECIPE_COL_1 +" integer PRIMARY KEY autoincrement, "
            + RECIPE_COL_2 + " text not null)";

    //Table Recipe_Categories
    public static final String RECIPE_CATEGORY_TABLE_NAME = "Recipe_Category";

    public static final String RECIPE_CATEGORY_CREATE_QUERY = "Create table "
            + RECIPE_CATEGORY_TABLE_NAME + " ( "
            + RECIPE_COL_1 +" integer not null REFERENCES "
                    +RECIPE_MASTER_TABLE_NAME + "( " +RECIPE_COL_1 + " ),"
            + COOKING_CATEGORY_COL_1 + " integer not null REFERENCES "
                    +COOKING_CATEGORY_TABLE_NAME + "( " +COOKING_CATEGORY_COL_1 + " ),"
            + REGIONAL_CATEGORY_COL_1 + " integer REFERENCES "
                    +REGIONAL_CATEGORY_TABLE_NAME + "( " +REGIONAL_CATEGORY_COL_1 + " ),"
            + OTHER_CATEGORY_COL_1 + " integer REFERENCES "
                    +OTHER_CATEGORY_TABLE_NAME + "( " +OTHER_CATEGORY_COL_1 + " ))";

    //Table Recipe_Ingredients
    public static final String RECIPE_INGREDIENT_TABLE_NAME = "Recipe_Ingredients";
    public static final String RECIPE_INGREDIENT_COL_3 = "quantity";
    public static final String RECIPE_INGREDIENT_COL_4 = "remarks";

    public static final String RECIPE_INGREDIENT_CREATE_QUERY = "Create table "
            + RECIPE_INGREDIENT_TABLE_NAME + " ( "
            + RECIPE_COL_1 +" integer not null REFERENCES "
                    +RECIPE_MASTER_TABLE_NAME + "( " +RECIPE_COL_1 + " ),"
            + INGREDIENTS_COL_1 + " integer not null REFERENCES "
                    +INGREDIENTS_TABLE_NAME + "( " +INGREDIENTS_COL_1 + " ),"
            + RECIPE_INGREDIENT_COL_3 + " real, "
            + RECIPE_INGREDIENT_COL_4 + " text)";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(COOKING_CATEGORY_CREATE_QUERY);
        db.execSQL(REGIONAL_CATEGORY_CREATE_QUERY);
        db.execSQL(OTHER_CATEGORY_CREATE_QUERY);
        db.execSQL(INGREDIENTS_CREATE_QUERY);
        db.execSQL(RECIPE_CREATE_QUERY);
        db.execSQL(RECIPE_MASTER_CREATE_QUERY);
        db.execSQL(RECIPE_CATEGORY_CREATE_QUERY);
        db.execSQL(RECIPE_INGREDIENT_CREATE_QUERY);

        //Inserting Values in Cooking_Category Table
        db.execSQL("INSERT INTO Cooking_Category (name) VALUES ('Stove')");
        db.execSQL("INSERT INTO Cooking_Category (name) VALUES ('Grill')");
        db.execSQL("INSERT INTO Cooking_Category (name) VALUES ('Microwave/Oven')");
        db.execSQL("INSERT INTO Cooking_Category (name) VALUES ('No Cooking')");

        //Inserting Values in Regional_Category Table
        db.execSQL("INSERT INTO Regional_Category (name) VALUES ('Indian')");
        db.execSQL("INSERT INTO Regional_Category (name) VALUES ('Mexican')");
        db.execSQL("INSERT INTO Regional_Category (name) VALUES ('Italian')");
        db.execSQL("INSERT INTO Regional_Category (name) VALUES ('Chinese')");
        db.execSQL("INSERT INTO Regional_Category (name) VALUES ('Thai')");
        db.execSQL("INSERT INTO Regional_Category (name) VALUES ('American')");

        //Inserting Values in Other_Category Table
        db.execSQL("INSERT INTO Other_Category (name) VALUES ('Healthy')");
        db.execSQL("INSERT INTO Other_Category (name) VALUES ('Cheap')");
        db.execSQL("INSERT INTO Other_Category (name) VALUES ('Quick & Easy')");

        //Inserting Values in Ingredients Table
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('penne', 'Box')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('frozen spinach', 'Box')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('chicken breasts', 'piece')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('italian seasoning', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('cayenne pepper', 'totaste')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('oil', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('prego spinach Florentine spaghetti sauce', 'Can')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('shredded cheese', 'Cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('feta ', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('No boil lasagna noodles', 'Box')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Mild Italian sausage', 'lb')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('pasta sauce', 'oz')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Shredded mozzarella', 'Cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Shredded parmesan cheese', 'Cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Ricotta cheese', 'Cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Salt', 'totaste')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Pepper', 'totaste')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Dried basil', 'totaste')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('cooked brown rice', 'Cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('onion (chopped)', 'Cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('corn, frozen', 'Cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('black beans', 'Can')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('chili powder', 'totaste')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('blue corn chips', 'totaste')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('guacamole', 'totaste')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('salsa', 'totaste')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('dice onions', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('dice tomatoes', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('lime ', 'slice')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('chicken noodle', 'pack')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('chicken season', 'pack')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('garlic clove', 'totaste')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Red kidney beans', 'Can')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Ginger (grated)', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Onion', 'piece')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Tomato', 'piece')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Cumin seeds', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Turmeric', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Coriander powder', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Cayenne pepper', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Cilantro', 'piece')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Garam masala', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Garlic salt', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Potatoes', 'piece')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Baby carrots', 'Cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Celery stalks (chopped)', 'cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Chicken broth', 'Can')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Dried parsley', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Thyme', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Nutmeg', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Paprika', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Low Fat Cooking Spray', 'totaste')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Thai 7 Spice', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Rice', 'grams')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Light Coconut Milk', 'ml')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Vegetable Stock', 'ml')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Green Beans (trimmed and chopped)', 'grams')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Skinless Salmon Fillets', 'grams')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('eggs', 'pieces')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Sun-dried tomatoes', 'cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Red potato', 'piece')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Green onion', 'piece')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Chopped broccoli', 'cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Garlic powder', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('spinach leaves', 'cups')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('bacon', 'slices')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Carrot', 'piece')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('chili season kit', 'pack')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('tomato sauce', 'oz')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('tomato juice', 'cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('diced tomatoes', 'can')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('great Northern beans', 'can')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('zucchini', 'piece')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('yellow squash', 'piece')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Hot dogs', 'Piece')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Hot dog buns', 'piece')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('AP BBQ Rub', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Macaroni and cheese', 'Box')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Nacho cheese sauce', 'Cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Shredded BBQ Meat', 'Cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('BBQ sauce', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Chicken drumsticks', 'Piece')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Ranch mix (dry)', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Powdered ginger', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Hamburger', 'lb')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Spaghetti sauce', 'cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Bread crumbs', 'cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Dry red wine', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Grated parmesan', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Lawry''s seasoning salt', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Provolone cheese', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('capsicums', 'piece')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('chaat masala', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('fresh cream', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('lemon juice', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('hung yoghurt', 'Cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('ginger garlic paste', 'Box')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('butter', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('flour', 'cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Backing Powder', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Granulated Sugar', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('mint', 'cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Cashews', 'cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Almonds', 'cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('boiled sweet corn', 'Cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Chana Dal', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('green peas', 'Cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('cocoa powder', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('milk', 'cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Ortega whole wheat wraps', 'piece')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Ground Beef', 'lb')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Monterrey Jack, grated', 'oz')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Flour Tortillas', 'piece')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Brinjal', 'piece')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Tomato puree', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('cooked rice', 'cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Cardomom powder', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Ghee', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('dried rubbed sage', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('roasting chicken', 'lb')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('butternut squash', 'cups')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('frozen meatless burger crumbles', 'oz')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('taco seasoning', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('tostada shells', 'piece')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('whole-grain Santa Fe rice', 'oz')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('turkey breast', 'cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('greek yogurt', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('lettuce', 'leaves')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('pickle spear', 'piece')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('pitted kalamata olives', 'cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('anchovy fillets', 'piece')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('capers', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('mustard', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('artichoke hearts', 'can')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('rolls', 'piece')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('fennel bulb', 'piece')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('chives', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('sliced cucumber', 'cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('sour cream', 'cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('minced chipotle chile', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('mayonnaise', 'cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('raisins', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('chicken spices powder', 'tbsp')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('cheddar cheese', 'cup')");
        db.execSQL("INSERT INTO Ingredients (name, unit) VALUES ('Bread', 'slice')");

        //Inserting Values in Recipe_Master Table
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Creamy Spinach and Chicken Pasta')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Skillet Lasagna')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Taco Bowl - Vegan')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Salsa Ramen Noodles')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Rajma - Red Kidney Bean Indian Curry')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Chicken Stew')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Paprika Chicken')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Thai Salmon Rice')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Hangover Frittata (Omelet)')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Grown-Up Grilled Cheese Sandwiches')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Big-Batch Veggie Chili')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Mac & Cheese Bacon wrapped Hot Dogs')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('BBQ Cheesy Quesadilla')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Ranch Chicken')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('3 Cheese Italian Burger')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Paneer Tikka ( Indian) ( cottage cheese)')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Indian Chicken Tikka')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Grilled Naan Filled with Herbs and Cheese')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Corn and Potato Kebabs')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Hara Bhara Kebab')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Easy Enchiladas')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('5 Minute Chocolate Mug Cake')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Microwave mashed potatoes')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Burrito Casserole')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Baingan Bhurta')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Baked Beans with Buttered Rice')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Carrot Halwa')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Roast Chicken with Potatoes and Butternut Squash')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Mexicali Meatless Tostadas')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Turkey Wrap')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Pan Bagnat')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Mediterranean Garden Wraps')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Chipotle Chicken Taco Salad')");
        db.execSQL(" INSERT INTO Recipe_Master (name) VALUES ('Apricot-Nut Turkey-Salad Sandwiches')");

        //Inserting Values in Recipe_Category Table
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (1,1,3,null)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (2,1,3,null)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (3,1,2,null)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (4,1,4,null)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (5,1,1,null)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (6,1,null,1)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (7,1,null,1)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (7,1,null,3)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (8,1,5,1)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (8,1,5,3)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (9,1,null,1)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (9,1,null,3)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (10,1,null,2)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (11,1,null,2)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (12,2,6,null)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (13,2,2,3)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (14,2,null,null)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (15,2,3,null)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (16,2,1,null)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (17,2,1,null)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (18,2,1,null)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (19,2,1,null)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (20,2,1,null)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (21,3,2,null)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (22,3,null,3)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (23,3,null,3)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (24,3,null,null)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (25,3,1,null)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (26,3,1,null)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (27,3,1,null)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (28,3,null,2)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (29,3,2,2)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (30,4,null,null)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (31,4,null,null)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (32,4,null,null)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (33,4,null,null)");
        db.execSQL(" INSERT INTO Recipe_Category (recipe_id, cook_id, region_id, other_id) VALUES (34,4,null,null)");

        //Inserting Values in Recipe_Ingredient Table
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (1,1,1,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (1,2,1,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (1,3,2,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (1,4,2,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (1,5,0,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (1,6,1,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (1,7,1.5,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (1,8,1,'optional')");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (1,9,2,'optional')");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (2,10,1,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (2,11,1,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (2,12,26,'Your favourite')");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (2,13,1.5,'divided')");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (2,14,0.5,'divided')");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (2,15,1,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (2,16,0,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (2,17,0,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (2,18,0,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (3,19,3,'cooked in vegetable broth')");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (3,20,0.33,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (3,21,0.33,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (3,22,1,'drained and rinsed')");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (3,23,0,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (3,16,0,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (3,6,1,'for sautéing')");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (3,24,0,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (3,25,0,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (3,26,0,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (6,3,3,'Cubed')");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (6,6,3,'Olive')");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (6,43,0.5,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (6,17,0.25,'Black Ground')");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (6,44,6,'Cut in half lengthwise')");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (6,45,1,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (6,35,6,'pearl')");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (6,46,2,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (6,47,3,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (6,48,0.5,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (6,49,0.25,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (6,50,0.25,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (7,3,4,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (7,51,1,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (7,95,0,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (7,143,1,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (7,16,0,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (7,17,0,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (7,6,1,'Olive, Extra Virgin')");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (10,35,1,'Vertically Sliced, Red')");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (10,32,0,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (10,144,1,'Shredded, White')");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (10,145,8,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (10,65,2,null)");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (10,36,2,'Slice')");
        db.execSQL("INSERT INTO Recipe_Ingredients (recipe_id, ingredient_id, quantity, remarks) VALUES (10,66,6,'Centre Cut, Cooked')");

        //Inserting Values in Table Recipe
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (6,1,'In a large, deep frying pan heat the olive oil on medium high.',2,NULL)");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (6,2,'Add the chicken and sprinkle with the garlic salt and black pepper.',1,'chicken_stew_1_fry')");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (6,3,'Cook the chicken till most of the moisture has cooked off.',5,'chicken_stew_2_cook_chicken')");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (6,4,'Then add the cans of chicken broth to the pan.',1,'chicken_stew_3_add_broth')");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (6,5,'Stir in the carrots and potatoes',2,'chicken_stew_4_add_potatoes_and_carrots')");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (6,6,'Simmer for about 10 minutes and add the onions and celery.',10,NULL)");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (6,7,'Simmer for 20 minutes more and add the spices.',20,'chicken_stew_5_add_spice_simmer')");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (6,8,'Simmer for another 10 minutes and serve hot.',10,'chicken_stew_6_final')");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (7,1,'Wash the chicken breast then add paprika, chicken spices, salt and pepper and squeeze a lemon',5,'paprika_chicken_1_add_paprikaandmasala')");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (7,2,'In a non-stick pan 1TB olive oil then add the chicken and fry',5,NULL)");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (7,3,'Done. Serve with wholegrain pasta for a healthy meal',1,'paprika_chicken_2_add_pasta_final')");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (10,1,'Heat a large nonstick skillet over medium-low heat. Coat pan with cooking spray. Add 1 cup onion and garlic; cook for 10 minutes or until tender and golden brown, stirring occasionally',10,'grilled_cheese_sandwich_1_fry_onion')");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (10,2,'Sprinkle 2 tablespoons cheese over each of 4 bread slices. Top each slice with 1/2 cup spinach, 2 tomato slices, 2 tablespoons onion mixture, and 1 1/2 bacon slices. Sprinkle each with 2 tablespoons cheese; top with the remaining 4 bread slices.',5,'grilled_cheese_sandwich_2_top_bread')");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (10,3,'Heat skillet over medium heat. Coat pan with cooking spray. Place sandwiches in pan, and cook for 3 minutes on each side or until golden brown and cheese melts.',3,'grilled_cheese_sandwich_3_final')");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (1,1,'Cube chicken and brown in a large sauce pan with seasonings and oil',10,'chicken_pasta_1_cube_chicken')");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (1,2,'Cook pasta until soft and add frozen spinach. Boil until spinach thaws',10,NULL)");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (1,3,'Drain pasta and add to pan with pasta sauce and chicken. Stir and let simmer about 5 mins',5,'chicken_pasta_2_add')");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (1,4,'Top with cheese and serve warm.',1,'chicken_pasta_3_final')");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (2,1,'In a large skillet over medium heat begin to cook and break down Italian sausage until no longer pink and cooked through 3/4 ways, drain remaining fat',10,'skillet_lasagna_1_cook_italian_suasage')");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (2,2,'While sausage is cooking, in a medium bowl mix together the ricotta cheese, 1/4 Parmesan cheese and 1/2 cup mozzarella cheese and add salt, pepper and basil to taste.',2,'skillet_lasagna_2_mix_cheese')");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (2,3,'Once sausage is almost cooked throughly add half of the pasta sauce',2,'skillet_lasagna_3_add_pasta_sauce')");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (2,4,'Cover the sauce mixture with 4 lasagna noodles, breaking noodles to fit pan',2,'skillet_lasagna_4_cover_with_lasagna')");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (2,5,'Spread cheese mixture evenly over noddles',1,NULL)");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (2,6,'Place 4 more noodles over cheese mixture, breaking the noodles to fit the pan',1,NULL)");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (2,7,'Pour remaining pasta sauce over noodles and top with the rest of the shredded Parmesan and shredded mozzarella cheese''Place 4 more noodles over cheese mixture, breaking the noodles to fit the pan',2,'skillet_lasagna_5_more_pasta_and_cheese')");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (2,8,'Cover skillet and simmer over medium low heat until noodles are tender, about 20-25 minutes''Pour remaining pasta sauce over noodles and top with the rest of the shredded Parmesan and shredded mozzarella cheese''Place 4 more noodles over cheese mixture, breaking the noodles to fit the pan',25,'skillet_lasagna_6_final')");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (3,1,'Coat pan with oil (I use grape seed oil). Saute chopped onion over medium heat until slightly brown.',6,NULL)");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (3,2,'Add corn to pan, cook until corn is no longer frozen.',3,'taco_bowl_1_add_ingredients')");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (3,3,'Add drained and rinsed beans to pan. Stirring occasionally.',10,'taco_bowl_2_stir')");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (3,4,'Add spices to taste.',3,NULL)");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (3,5,'Add cooked brown rice to pan. Mix thoroughly. Cook until everything is heated through.',5,NULL)");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (3,6,'Spoon rice, beans, corn, and onion mixture into bowls.',2,'taco_bowl_3_add_cooked_rice_stir')");
        db.execSQL("INSERT INTO Recipe (recipe_id, step_no, step, step_time, image_path) VALUES (3,7,'Serve with blue corn tortilla chips, guacamole, and salsa.',1,'taco_bowl_4_final')");




    }

    public List GetQuickAndEasyRecipes()
    {
         List QuickandEasyTitles = new ArrayList();

        Cursor c =
                db.rawQuery("SELECT c.name FROM Other_Category a, Recipe_Category b, Recipe_Master c WHERE a.name = 'Quick & Easy' AND a.other_id = b.other_id AND b.recipe_id = c.recipe_id",null);
        if(c!=null)
        {
            if(c.moveToFirst())
            {
                do {
                    String recipename = c.getString(0);
                    QuickandEasyTitles.add(recipename);
                }while (c.moveToNext());
            }
        }
        return QuickandEasyTitles;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + COOKING_CATEGORY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + REGIONAL_CATEGORY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + OTHER_CATEGORY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + INGREDIENTS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RECIPE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RECIPE_MASTER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RECIPE_CATEGORY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RECIPE_INGREDIENT_CREATE_QUERY);
        onCreate(db);
    }
}
