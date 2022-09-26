import { StyleSheet, Text, View, Button } from 'react-native';
import MoviesList from '.././MoviesList';

export default function PopularMoviesScreen({navigation}) {

  return (
    <View style={styles.container}>
        <Text style={styles.title}>
            Popular movies!
        </Text>
        <Text style={styles.bread}>Click on a movie to see details!</Text>
        <MoviesList navigation={navigation}/>
        <Button
            style={{alignSelf: "flex-start",}}
            color="#F8B7CD"
            title="Go Home"
            onPress={() => navigation.navigate('Home')}
          />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: '#C8E7F5',
    alignItems: 'center',
    justifyContent: 'center',
  },
  title: {
    fontSize: 30,
    margin: 20,
    fontWeight: "bold",
    color: "#0671B7",
  },
  bread: {
    fontSize: 16,
    marginBottom: 10,
  }
});
