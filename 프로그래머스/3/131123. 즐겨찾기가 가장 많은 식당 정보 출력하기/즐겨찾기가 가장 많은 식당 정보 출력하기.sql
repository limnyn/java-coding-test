SELECT r.food_type, r.rest_id, r.rest_name, r.favorites
FROM rest_info r
JOIN rest_info s
  ON r.food_type = s.food_type
GROUP BY r.food_type, r.rest_id, r.rest_name, r.favorites
HAVING r.favorites = MAX(s.favorites)
ORDER BY r.food_type DESC;
