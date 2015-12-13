import ast

line = input()
json = ast.literal_eval(line)


def recursive(_json, Sum):
    if type(_json) is dict:
        if 'red' not in _json.values():
            for key, value in _json.items():
                if type(key) is int:
                    Sum[0] += key

                recursive(value, Sum)

    elif type(_json) is list:
        for value in _json:
            recursive(value, Sum)

    else:
        if type(_json) is int:
            Sum[0] += _json

Sum = [0]
recursive(json, Sum)
print(Sum[0])
