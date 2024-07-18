-- -- Create sequences if they do not exist
-- CREATE SEQUENCE IF NOT EXISTS category_seq INCREMENT BY 50;
-- CREATE SEQUENCE IF NOT EXISTS product_seq INCREMENT BY 50;

-- Insert data into category table and capture the IDs
WITH ins AS (
    INSERT INTO category (id, name, description)
        VALUES
            (nextval('category_seq'), 'Electronics', 'Devices and gadgets'),
            (nextval('category_seq'), 'Books', 'Various genres of books'),
            (nextval('category_seq'), 'Clothing', 'Apparel for men, women, and children'),
            (nextval('category_seq'), 'Home Appliances', 'Appliances for home use'),
            (nextval('category_seq'), 'Sports', 'Sporting goods and equipment'),
            (nextval('category_seq'), 'Beauty', 'Beauty and personal care products'),
            (nextval('category_seq'), 'Toys', 'Toys and games for kids'),
            (nextval('category_seq'), 'Furniture', 'Home and office furniture'),
            (nextval('category_seq'), 'Automotive', 'Car accessories and parts'),
            (nextval('category_seq'), 'Food', 'Grocery and gourmet food')
        RETURNING id, name
)

-- Insert data into product table using captured category IDs
INSERT INTO product (id, name, description, available_quantity, price, category_id)
SELECT nextval('product_seq'), 'Smartphone', 'Latest model smartphone with advanced features', 50, 699.99, id FROM ins WHERE name = 'Electronics'
UNION ALL
SELECT nextval('product_seq'), 'Laptop', 'High-performance laptop for gaming and work', 30, 999.99, id FROM ins WHERE name = 'Electronics'
UNION ALL
SELECT nextval('product_seq'), 'Tablet', 'Portable tablet with high-resolution display', 40, 299.99, id FROM ins WHERE name = 'Electronics'
UNION ALL
SELECT nextval('product_seq'), 'Headphones', 'Noise-cancelling over-ear headphones', 100, 199.99, id FROM ins WHERE name = 'Electronics'
UNION ALL
SELECT nextval('product_seq'), 'Smartwatch', 'Smartwatch with fitness tracking features', 75, 149.99, id FROM ins WHERE name = 'Electronics'
UNION ALL
SELECT nextval('product_seq'), 'E-book Reader', 'E-book reader with high-contrast screen', 60, 89.99, id FROM ins WHERE name = 'Books'
UNION ALL
SELECT nextval('product_seq'), 'Fiction Book', 'Bestselling fiction book by famous author', 150, 14.99, id FROM ins WHERE name = 'Books'
UNION ALL
SELECT nextval('product_seq'), 'Non-fiction Book', 'Popular non-fiction book on a trending topic', 120, 19.99, id FROM ins WHERE name = 'Books'
UNION ALL
SELECT nextval('product_seq'), 'Children''s Book', 'Illustrated book for children', 200, 9.99, id FROM ins WHERE name = 'Books'
UNION ALL
SELECT nextval('product_seq'), 'Cookbook', 'Recipe book with easy-to-follow instructions', 80, 24.99, id FROM ins WHERE name = 'Books'
UNION ALL
SELECT nextval('product_seq'), 'T-shirt', 'Cotton t-shirt available in various sizes', 300, 9.99, id FROM ins WHERE name = 'Clothing'
UNION ALL
SELECT nextval('product_seq'), 'Jeans', 'Denim jeans with a comfortable fit', 200, 39.99, id FROM ins WHERE name = 'Clothing'
UNION ALL
SELECT nextval('product_seq'), 'Jacket', 'Warm jacket for cold weather', 100, 59.99, id FROM ins WHERE name = 'Clothing'
UNION ALL
SELECT nextval('product_seq'), 'Dress', 'Fashionable dress for special occasions', 150, 49.99, id FROM ins WHERE name = 'Clothing'
UNION ALL
SELECT nextval('product_seq'), 'Shoes', 'Stylish shoes for everyday wear', 250, 29.99, id FROM ins WHERE name = 'Clothing'
UNION ALL
SELECT nextval('product_seq'), 'Microwave', 'Compact microwave oven with multiple settings', 60, 99.99, id FROM ins WHERE name = 'Home Appliances'
UNION ALL
SELECT nextval('product_seq'), 'Refrigerator', 'Energy-efficient refrigerator with large capacity', 20, 499.99, id FROM ins WHERE name = 'Home Appliances'
UNION ALL
SELECT nextval('product_seq'), 'Washing Machine', 'High-efficiency washing machine', 15, 399.99, id FROM ins WHERE name = 'Home Appliances'
UNION ALL
SELECT nextval('product_seq'), 'Vacuum Cleaner', 'Powerful vacuum cleaner with HEPA filter', 80, 149.99, id FROM ins WHERE name = 'Home Appliances'
UNION ALL
SELECT nextval('product_seq'), 'Blender', 'High-speed blender for smoothies and shakes', 100, 49.99, id FROM ins WHERE name = 'Home Appliances'
UNION ALL
SELECT nextval('product_seq'), 'Tennis Racket', 'Lightweight tennis racket for beginners', 50, 29.99, id FROM ins WHERE name = 'Sports'
UNION ALL
SELECT nextval('product_seq'), 'Football', 'Official size and weight football', 120, 19.99, id FROM ins WHERE name = 'Sports'
UNION ALL
SELECT nextval('product_seq'), 'Basketball', 'Indoor/outdoor basketball', 100, 24.99, id FROM ins WHERE name = 'Sports'
UNION ALL
SELECT nextval('product_seq'), 'Yoga Mat', 'Non-slip yoga mat with carrying strap', 150, 19.99, id FROM ins WHERE name = 'Sports'
UNION ALL
SELECT nextval('product_seq'), 'Dumbbells', 'Set of adjustable dumbbells', 80, 49.99, id FROM ins WHERE name = 'Sports'
UNION ALL
SELECT nextval('product_seq'), 'Shampoo', 'Gentle shampoo for daily use', 200, 7.99, id FROM ins WHERE name = 'Beauty'
UNION ALL
SELECT nextval('product_seq'), 'Conditioner', 'Moisturizing conditioner for all hair types', 180, 8.99, id FROM ins WHERE name = 'Beauty'
UNION ALL
SELECT nextval('product_seq'), 'Face Cream', 'Anti-aging face cream with SPF', 100, 19.99, id FROM ins WHERE name = 'Beauty'
UNION ALL
SELECT nextval('product_seq'), 'Perfume', 'Luxury perfume with a floral scent', 50, 59.99, id FROM ins WHERE name = 'Beauty'
UNION ALL
SELECT nextval('product_seq'), 'Lipstick', 'Long-lasting lipstick available in various shades', 250, 14.99, id FROM ins WHERE name = 'Beauty'
UNION ALL
SELECT nextval('product_seq'), 'Action Figure', 'Action figure with movable parts', 200, 14.99, id FROM ins WHERE name = 'Toys'
UNION ALL
SELECT nextval('product_seq'), 'Puzzle', '1000-piece jigsaw puzzle', 150, 12.99, id FROM ins WHERE name = 'Toys'
UNION ALL
SELECT nextval('product_seq'), 'Board Game', 'Strategy board game for all ages', 100, 29.99, id FROM ins WHERE name = 'Toys'
UNION ALL
SELECT nextval('product_seq'), 'Toy Car', 'Remote-controlled toy car', 120, 19.99, id FROM ins WHERE name = 'Toys'
UNION ALL
SELECT nextval('product_seq'), 'Doll', 'Fashion doll with accessories', 180, 24.99, id FROM ins WHERE name = 'Toys'
UNION ALL
SELECT nextval('product_seq'), 'Sofa', 'Comfortable three-seater sofa', 20, 399.99, id FROM ins WHERE name = 'Furniture'
UNION ALL
SELECT nextval('product_seq'), 'Dining Table', 'Modern dining table for six', 15, 299.99, id FROM ins WHERE name = 'Furniture'
UNION ALL
SELECT nextval('product_seq'), 'Office Chair', 'Ergonomic office chair with lumbar support', 50, 149.99, id FROM ins WHERE name = 'Furniture'
UNION ALL
SELECT nextval('product_seq'), 'Bookshelf', 'Wooden bookshelf with adjustable shelves', 30, 89.99, id FROM ins WHERE name = 'Furniture'
UNION ALL
SELECT nextval('product_seq'), 'Bed Frame', 'Queen size bed frame with storage', 25, 199.99, id FROM ins WHERE name = 'Furniture'
UNION ALL
SELECT nextval('product_seq'), 'Car Battery', 'Long-lasting car battery', 50, 79.99, id FROM ins WHERE name = 'Automotive'
UNION ALL
SELECT nextval('product_seq'), 'Tire', 'All-season tire for passenger cars', 80, 59.99, id FROM ins WHERE name = 'Automotive'
UNION ALL
SELECT nextval('product_seq'), 'Motor Oil', 'Synthetic motor oil for engines', 200, 29.99, id FROM ins WHERE name = 'Automotive'
UNION ALL
SELECT nextval('product_seq'), 'Car Cover', 'Weatherproof car cover', 100, 49.99, id FROM ins WHERE name = 'Automotive'
UNION ALL
SELECT nextval('product_seq'), 'Car Seat Cover', 'Comfortable car seat cover', 150, 19.99, id FROM ins WHERE name = 'Automotive'
UNION ALL
SELECT nextval('product_seq'), 'Cereal', 'Box of whole grain cereal', 300, 3.99, id FROM ins WHERE name = 'Food'
UNION ALL
SELECT nextval('product_seq'), 'Pasta', 'Package of whole wheat pasta', 250, 1.99, id FROM ins WHERE name = 'Food'
UNION ALL
SELECT nextval('product_seq'), 'Olive Oil', 'Bottle of extra virgin olive oil', 180, 9.99, id FROM ins WHERE name = 'Food'
UNION ALL
SELECT nextval('product_seq'), 'Coffee', 'Bag of ground coffee', 200, 7.99, id FROM ins WHERE name = 'Food'
UNION ALL
SELECT nextval('product_seq'), 'Tea', 'Box of herbal tea bags', 150, 4.99, id FROM ins WHERE name = 'Food'
UNION ALL
SELECT nextval('product_seq'), 'Chocolate', 'Bar of dark chocolate', 300, 2.99, id FROM ins WHERE name = 'Food'
UNION ALL
SELECT nextval('product_seq'), 'Wine', 'Bottle of red wine', 100, 12.99, id FROM ins WHERE name = 'Food'
UNION ALL
SELECT nextval('product_seq'), 'Cheese', 'Block of cheddar cheese', 120, 5.99, id FROM ins WHERE name = 'Food'
UNION ALL
SELECT nextval('product_seq'), 'Yogurt', 'Container of Greek yogurt', 180, 1.99, id FROM ins WHERE name = 'Food'
UNION ALL
SELECT nextval('product_seq'), 'Bread', 'Loaf of whole grain bread', 200, 3.49, id FROM ins WHERE name = 'Food';
