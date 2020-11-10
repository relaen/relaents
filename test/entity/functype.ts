
import {FuncCompany} from './funccompany'
import { Entity, Id, Column, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_func_type",'tunnel')
export class FuncType extends BaseEntity{
	@Id()
	@Column({
		name:'func_type_id',
		type:'int',
		nullable:false
	})
	funcTypeId:number;

	@Column({
		name:'func_type_name',
		type:'string',
		nullable:true
	})
	funcTypeName:string;

	@OneToMany({entity:'FuncCompany',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'funcType',lazyFetch:true})
	funcCompanys:Array<FuncCompany>;

}