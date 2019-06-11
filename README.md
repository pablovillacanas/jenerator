

# Jenerator

Jenerator is a simple library that allows developers to create random instances of custom classes. The generation of those instances can be controlled by annotations on their fields, allowing to constraint the values generated for each one. Ones of the jenerator capabilities are:  

  - Generate as simple as putting an annotation over a class and one call to the API, *n* intances of custom objects. 
  - Constraints, field by field, the possibles values that the API can generate for each field, including percent of nulls or if it has to be unique.
  - Provide possible values for each field by passing a text file.

# Start now

You can start right now to generate random instances of your classes. First mark your class as generable.

    @Generable
    public class Foo{
        Integer number;
        //getters and setters
    }

Then we only have to call the main method of the API.

    List<Foo> instances = Jenerator.generate(Foo.class, 20);

As we didn't constraint the number attribute, it will be populated by a random number.

# Field Constraints

Currently, Jenerator supports the following field types to be generated, that in case you want be constrained, have to be annotated by their correspondent annotation.

<table>
  <thead>
    <tr>
      <th>Java Type</th>
      <th>Annotation Type</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Long</td>
      <td rowspan="4">NaturalNumberGenerable</td>
    </tr>
    <tr>
      <td>Integer</td>
    </tr>
    <tr>
      <td>Short</td>
    </tr>
    <tr>
      <td>Byte</td>
    </tr>
    <tr>
      <td>Double</td>
      <td rowspan="2">DecimalNumberGenerable</td>
    </tr>
    <tr>
      <td>Float</td>
    </tr>
    <tr>
      <td>String</td>
      <td rowspan="2">StringGenerable</td>
    </tr>
    <tr>
      <td>Character</td>
    </tr>
    <tr>
      <td>Boolean</td>
      <td>BooleanGenerable</td>
    </tr>
    <tr>
      <td>Object</td>
      <td>Generable</td>
    </tr>
    <tr>
      <td>*</td>
      <td>NoGenerable</td>
    </tr>
  </tbody>
</table>


> Custom objects associated with others that you want to be generated as well, does not have to be annotated in the associated class at all. Instead, you have to mark them as Generable at level class. If you don't want that field to be generated, simply annotated it as NoGenerable.

## CommonConstraints

NaturalNumberGenerable, DecimalNumberGenerable and StringGenerable are composed by another annotation: **GenerationConstraints**.
This annotation has the following attributes:

 - *nullable*: A number between 0 and 1 that tells generator what is the percent of instances which have that field null, e.g. if it's value equals to 0.5, half of the objects generated will have null setted for that field. Defaults to zero.
 - *unique*: Specify if this field must have an unique value in all the generated collection. Defaults to false.
 - *source*: Specify the name of the text file under the resource folder from which values will be extracted. Defaults to none.

This three annotations are related between them and there are not exclusive. Furthermore, they constraint the generation of values for annotations that include them.

> Source file must be allocated in /src/main/resources

## NaturalNumberConstraints
In addition to the inner annotation that can be setted, NaturalNumberConstraints has the following attributes.

 - *maxValue* : Maximum value included in the generation of values -included. Defaults to maximum value of field type.
 - *minValue*: Minimum value included in the generation of values - included. Defaults to minimum value of field type.

## DecimalNumberConstraints
In addition to the inner annotation that can be setted, DecimalNumberConstraints has the following attributes.

 - *maxValue* : Maximum value included in the generation of values -included. Defaults to maximum value of field type.
 - *minValue*: Minimum value included in the generation of values - included. Defaults to minimum value of field type.
 - *precision*: number of decimal digits. Defaults to 2.

## StringConstraints
In addition to the inner annotation that can be setted, StringConstraints has the following attributes.

 - *maxLenght* : Maximum characters of generated string -included. Defaults to 5.
 - *minLenght*: Minimum characters of generated string - included. Defaults to 10.
 - *style*: Format of string generated. Defaults to ONLY_LETTERS.

## BooleanGenerable

This annotation is the only one, excluding NoGenerable, that not supports the inner GenerationConstraint. It has not sense be otherwise.

 - *relationTrueFalse*: Defines the relation between true and false values. The values specified here determines how many true values are going to be generated regarding false ones. A value 0.7 defines that 7 out of 10 values will be true and 3 out of 10 false. Also you can no determine this value setting it to one less than 0. In this case, true and false will be randomly generated.
 - *nullable*: Specify how many null values will be generated. It does not affect to the relationTrueFalse for the rest of values that have to be generated. Defaults to zero.


