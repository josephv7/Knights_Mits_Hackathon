const recommendations = {
  "vitamin-a": {
    0: {
      text:
        "Have more food items like Beef Liver, Carrots, Sweet Potato that have high Vitamin A",
      food: ["Beef Liver", "Carrots", "Sweet Potato"],
      status: true
    },
    1: {
      text:
        "Have more food items like Beef Liver, Carrots, Sweet Potato that have high Vitamin A",
      food: ["Beef Liver", "Carrots", "Sweet Potato"],
      status: true
    },
    2: {
      text: "",
      food: [],
      status: true
    },
    3: {
      text:
        "Have less food items with high Vitamin A content like Beef Liver, Carrots, Sweet Potato",
      food: ["Beef Liver", "Carrots", "Sweet Potato"],
      status: false
    },
    4: {
      text:
        "Have less food items with high Vitamin A content like Beef Liver, Carrots, Sweet Potato",
      food: ["Beef Liver", "Carrots", "Sweet Potato"],
      status: false
    }
  },
  "vitamin-b12": {
    0: {
      text: "Have more food items like Eggs, Milk, Cheese",
      food: ["Eggs", "Milk", "Cheese"],
      status: true
    },
    1: {
      text: "Have more food items like Eggs, Milk, Cheese",
      food: ["Eggs", "Milk", "Cheese"],
      status: true
    },
    2: {
      text: "",
      food: [],
      status: true
    },
    3: {
      text:
        "Have less food items with high Vitamin B12 content like Eggs, Milk, Cheese",
      food: ["Eggs", "Milk", "Cheese"],
      status: false
    },
    4: {
      text:
        "Have less food items with high Vitamin B12 content like Eggs, Milk, Cheese",
      food: ["Eggs", "Milk", "Cheese"],
      status: false
    }
  },
  "vitamin-d": {
    0: {
      text: "Have more food items like Strawberries, Citrus Fruits, Pappaya",
      food: ["Strawberries", "Citrus Fruits", "Pappaya"],
      status: true
    },
    1: {
      text: "Have more food items like Strawberries, Citrus Fruits, Pappaya",
      food: ["Strawberries", "Citrus Fruits", "Pappaya"],
      status: true
    },
    2: {
      text: "",
      food: [],
      status: false
    },
    3: {
      text:
        "Have less food items with high Vitamin D content like Strawberries, Citrus Fruits, Pappaya",
      food: ["Strawberries", "Citrus Fruits", "Pappaya"],
      status: false
    },
    4: {
      text:
        "Have less food items with high Vitamin D content like Strawberries, Citrus Fruits, Pappaya",
      food: ["Strawberries", "Citrus Fruits", "Pappaya"],
      status: false
    }
  },
  "vitamin-e": {
    0: {
      text: "Have more food items like Tuna, Dairy Products, Beef Liver",
      status: true,
      food: ["Tuna", "Diary Products", "Beef Liver"]
    },
    1: {
      text: "Have more food items like Tuna, Dairy Products, Beef Liver",
      status: true,
      food: ["Tuna", "Diary Products", "Beef Liver"]
    },
    2: {
      text: "",
      status: true,
      food: ["Tuna", "Diary Products", "Beef Liver"]
    },
    3: {
      text:
        "Have less food items with high Vitamin D content like Tuna, Dairy Products, Beef Liver",
      status: false,
      food: ["Tuna", "Diary Products", "Beef Liver"]
    },
    4: {
      text:
        "Have less food items with high Vitamin D content like Tuna, Dairy Products, Beef Liver",
      status: false,
      food: ["Tuna", "Diary Products", "Beef Liver"]
    }
  },
  iron: {
    0: {
      text: "Have more food items like Legumes, Lentils, Nuts and Seeds",
      status: true,
      food: ["Legumes", "Lentils", "Nuts and Seeds"]
    },
    1: {
      text: "Have more food items like Legumes, Lentils, Nuts and Seeds",
      status: true,
      food: ["Legumes", "Lentils", "Nuts and Seeds"]
    },
    2: {
      text: "",
      status: true,
      food: ["Legumes", "Lentils", "Nuts and Seeds"]
    },
    3: {
      text:
        "Have less food items with high Iron content like Legumes, Lentils, Nuts and Seeds",
      status: false,
      food: ["Legumes", "Lentiles", "Nuts and Seeds"]
    },
    4: {
      status: false,
      text:
        "Have less food items with high Iron content like Legumes, Lentils, Nuts and Seeds",
      food: ["Legumes", "Lentiles", "Nuts and Seeds"]
    }
  }
};

module.exports = recommendations;
