import { StyleSheet, Text, View, Button, Image } from 'react-native';
import { useEffect, useState } from 'react';

export default function MovieDetailScreen({route, navigation}) {
  const {id, title, release_date, poster_path, overview, vote_average} = route.params;
  useEffect(() => console.log("here"),[]);
  return (
    <View style={styles.container}>
        <Text style={styles.title}>{title}</Text>
        <Text style={styles.date}>{release_date}</Text>
        <Image
              style={styles.img}
              source={{
                uri: 'https://image.tmdb.org/t/p/w400/' + poster_path,
              }}
            />
        <Text style={styles.h2}>Overview</Text>
        <Text style={styles.bread}>{overview}</Text>
        <Text style={styles.h2}>IMDB Score</Text>
        <Text style={styles.bread}>{vote_average} / 10</Text>
        <Button
            color="#F8B7CD"
            title="Go Back"
            onPress={() => navigation.navigate('Movies')}
          />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: '#C8E7F5',
    textAlign: "center",
  },
  title: {
    marginTop: 20,
    fontSize: 30,
    marginBottom: 10,
    fontWeight: "bold",
    color: "#0671B7",
  },
  img: {
    width: "300px",
    height: "450px",
    border: "1px solid #FFF",
    borderRadius: 15,
    alignSelf: "center",
  },
  date: {
    fontSize: "12",
    color: "#F675A8",
    marginBottom: 14,
  },
  h2: {
    fontSize: 24,
    textAlign: "left",
    marginLeft: 20,
    marginTop: 20,
    fontWeight: "bold",
    color: "#67A3D9",
  },
  bread: {
    margin: 20,
    textAlign: "left",
    fontSize: 18,
  }
});